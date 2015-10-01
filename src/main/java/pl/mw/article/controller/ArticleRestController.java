package pl.mw.article.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.mw.article.dao.ArticleDAO;
import pl.mw.article.domain.Article;

import java.util.Set;

/**
 * Created by mwiesiolek on 30/09/2015.
 */
@RestController
@RequestMapping(value = "/rest/article")
public class ArticleRestController {
    private static final Logger LOGGER = LogManager.getLogger(ArticleRestController.class);

    @Autowired
    private ArticleDAO articleDAO;

    @RequestMapping(value = "/find")
    public Set<Article> getArticles(@RequestParam Integer start,
                                    @RequestParam Integer number,
                                    @RequestParam(required = false) String authorFirstName,
                                    @RequestParam(required = false) String authorSurname,
                                    @RequestParam(required = false) String startDate,
                                    @RequestParam(required = false) String endDate,
                                    @RequestParam(required = false) String keyword) {
        LOGGER.debug("REST method /rest/article/find was requested with given parameters: start = {}, number = {}, authorFirstName = {}, startDate = {}, endDate = {}, keyword = {}", start, number, authorFirstName, startDate, endDate, keyword);

        Set<Article> articles;
        if(StringUtils.isBlank(authorFirstName) &&
                StringUtils.isBlank(authorSurname) &&
                StringUtils.isBlank(startDate) &&
                StringUtils.isBlank(endDate) &&
                StringUtils.isBlank(keyword)){

            articles = articleDAO.findAll(start, number);
        }else{

            Criterion allCriterions = null;
            if(!StringUtils.isBlank(authorFirstName)){
                allCriterions = Restrictions.like("a.firstName", "%" + authorFirstName + "%");
            }

            if(!StringUtils.isBlank(authorSurname)){
                if(allCriterions == null){
                    allCriterions = Restrictions.like("a.surname", "%" + authorSurname + "%");
                }else{
                    allCriterions = Restrictions.and(allCriterions, Restrictions.like("a.surname", "%" + authorSurname + "%"));
                }
            }

            if(!StringUtils.isBlank(startDate)){
                if(allCriterions == null){
                    allCriterions = Restrictions.eq("publishDate", startDate);
                }else{
                    allCriterions = Restrictions.and(allCriterions, Restrictions.eq("publishDate", startDate));
                }
            }

            if(!StringUtils.isBlank(endDate)){
                if(allCriterions == null){
                    allCriterions = Restrictions.eq("publishDate", endDate);
                }else{
                    allCriterions = Restrictions.and(allCriterions, Restrictions.eq("publishDate", endDate));
                }
            }

            if(!StringUtils.isBlank(keyword)) {
                if(allCriterions == null){
                    allCriterions = Restrictions.like("k.word", "%" + keyword + "%");
                }else{
                    allCriterions = Restrictions.and(allCriterions, Restrictions.like("k.word", "%" + keyword + "%"));
                }
            }

            articles = articleDAO.findAllWith(allCriterions, start, number);
        }

        return articles;
    }
}
