package pl.mw.article.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.mw.article.dao.ArticleDAO;
import pl.mw.article.domain.Article;
import pl.mw.article.enums.Alert;

import java.util.Set;

/**
 * Created by mwiesiolek on 30/09/2015.
 */
@Controller
@RequestMapping(value = "//article")
public class ArticleController {
    private static final Logger LOGGER = LogManager.getLogger(ArticleController.class);
    public static final int ARTICLES_UPPER_BOUND = 10;

    @Autowired
    private ArticleDAO articleDAO;

    @RequestMapping(value = "/list")
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView("article/list");

        final Set<Article> articles = articleDAO.findAll(0, ARTICLES_UPPER_BOUND);
        LOGGER.info("Found given number of articles: {}", articles.size());

        modelAndView.addObject("articles", articles);

        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute Article article){
        ModelAndView modelAndView = new ModelAndView("article/manage");

        articleDAO.saveOrUpdate(article);

        if(article.getId() > 0){
            LOGGER.info("Article with given id: {} was persisted in DB.", article.getId());

            modelAndView.addObject(Alert.NOTIFICATION.getKey(), "Article was not added. Try again.");
        }else{
            LOGGER.warn("Article was not persisted in DB.");

            modelAndView.addObject(Alert.SUCCESS.getKey(), "Article was added.");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam Long id){
        ModelAndView modelAndView = new ModelAndView("article/manage");

        final Article article = articleDAO.find(id);
        if(article == null){
            LOGGER.warn("Article with given id {} was not found in DB.");

            modelAndView.addObject(Alert.NOTIFICATION.getKey(), String.format("Article with given id %d was not found.", id));
        }else{
            LOGGER.info("Article with given id {} found in DB.", article);

            modelAndView.addObject("article", article);
        }

        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@ModelAttribute Article article){
        ModelAndView modelAndView = new ModelAndView("article/manage");

        articleDAO.saveOrUpdate(article);

        return modelAndView;
    }
}
