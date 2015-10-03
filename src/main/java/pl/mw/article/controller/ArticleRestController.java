package pl.mw.article.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.mw.article.domain.Article;
import pl.mw.article.service.ArticleService;
import pl.mw.article.viewmodel.json.ArticleWrapper;

import java.text.ParseException;
import java.util.Set;
import java.util.TimeZone;

/**
 * Created by mwiesiolek on 30/09/2015.
 */
@RestController
@RequestMapping(value = "/rest/article")
public class ArticleRestController {
    private static final Logger LOGGER = LogManager.getLogger(ArticleRestController.class);

    private static final FastDateFormat dateTimeFormatter = FastDateFormat.getInstance("yyyy-MM-dd", TimeZone.getTimeZone("GMT"));

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArticleWrapper getArticles(@RequestParam Integer start,
                                    @RequestParam Integer number,
                                    @RequestParam(required = false) String authorFirstName,
                                    @RequestParam(required = false) String authorSurname,
                                    @RequestParam(required = false) String sStartDate,
                                    @RequestParam(required = false) String sEndDate,
                                    @RequestParam(required = false) String keyword) throws ParseException {
        LOGGER.debug("REST method /rest/article/find was requested with given parameters: start = {}, number = {}, authorFirstName = {}, authorSurname = {},startDate = {}, endDate = {}, keyword = {}", start, number, authorFirstName, authorSurname, sStartDate, sEndDate, keyword);

        Long startDate = sStartDate == null ? null : Long.valueOf(dateTimeFormatter.parse(sStartDate).getTime());
        Long endDate = sEndDate == null ? null : Long.valueOf(dateTimeFormatter.parse(sEndDate).getTime());

        Set<Article> articles;
        ArticleWrapper articleWrapper;
        if(StringUtils.isBlank(authorFirstName) &&
                StringUtils.isBlank(authorSurname) &&
                (startDate == null) &&
                (endDate == null) &&
                StringUtils.isBlank(keyword)){

            articles = articleService.findAll(start, number);
        }else{

            Criterion allCriterions = null;
            if(!StringUtils.isBlank(authorFirstName)){
                allCriterions = Restrictions.like("authors.firstName", "%" + authorFirstName + "%");
            }

            if(!StringUtils.isBlank(authorSurname)){
                if(allCriterions == null){
                    allCriterions = Restrictions.like("authors.surname", "%" + authorSurname + "%");
                }else{
                    allCriterions = Restrictions.and(allCriterions, Restrictions.like("authors.surname", "%" + authorSurname + "%"));
                }
            }

            if(!(startDate == null) && endDate == null){
                if(allCriterions == null){
                    allCriterions = Restrictions.gt("publishDate", startDate);
                }else{
                    allCriterions = Restrictions.and(allCriterions, Restrictions.gt("publishDate", startDate));
                }
            }

            if(!(endDate == null) && startDate == null){
                if(allCriterions == null){
                    allCriterions = Restrictions.lt("publishDate", endDate);
                }else{
                    allCriterions = Restrictions.and(allCriterions, Restrictions.lt("publishDate", endDate));
                }
            }

            if(!(endDate == null) && !(startDate == null)){
                if(allCriterions == null){
                    allCriterions = Restrictions.between("publishDate", startDate, endDate);
                }else{
                    allCriterions = Restrictions.and(allCriterions, Restrictions.between("publishDate", startDate, endDate));
                }
            }

            if(!StringUtils.isBlank(keyword)) {
                if(allCriterions == null){
                    allCriterions = Restrictions.like("keywords.word", "%" + keyword + "%");
                }else{
                    allCriterions = Restrictions.and(allCriterions, Restrictions.like("keywords.word", "%" + keyword + "%"));
                }
            }

            articles = articleService.findAllWith(allCriterions, start, number);
        }

        Long size = articleService.size();
        articleWrapper = new ArticleWrapper(articles, size, number);

        return articleWrapper;
    }
}
