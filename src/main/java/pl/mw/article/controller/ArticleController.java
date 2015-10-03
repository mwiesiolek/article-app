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
import pl.mw.article.domain.Author;
import pl.mw.article.domain.Keyword;
import pl.mw.article.domain.builder.ArticleBuilder;
import pl.mw.article.enums.Alert;
import pl.mw.article.service.ArticleService;
import pl.mw.article.service.AuthorService;
import pl.mw.article.service.KeywordService;
import pl.mw.article.viewmodel.ArticleView;

import java.util.Set;

import static pl.mw.article.enums.Alert.*;

/**
 * Created by mwiesiolek on 30/09/2015.
 */
@Controller
public class ArticleController {
    private static final Logger LOGGER = LogManager.getLogger(ArticleController.class);
    public static final int ARTICLES_UPPER_BOUND = 10;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private KeywordService keywordService;

    @RequestMapping(value = "/")
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView("article/list");

        final Set<Article> articles = articleService.findAll(0, ARTICLES_UPPER_BOUND);
        LOGGER.info("Found given number of articles: {}", articles.size());

        modelAndView.addObject("articles", articles);

        return modelAndView;
    }

    @RequestMapping(value = "/article/add")
    public ModelAndView add(){
        ModelAndView modelAndView = new ModelAndView("article/manage");

        Set<Author> authors = authorService.findAll(0, authorService.size().intValue());
        modelAndView.addObject("authors", authors);

        Set<Keyword> keywords = keywordService.findAll(0, keywordService.size().intValue());
        modelAndView.addObject("keywords", keywords);

        ArticleView articleView = new ArticleView(new Article());
        modelAndView.addObject("articleView", articleView);

        return modelAndView;
    }

    @RequestMapping(value = "/article/add", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute ArticleView articleView){
        ModelAndView modelAndView = new ModelAndView("article/manage");

        Article articleWithPublishDate = ArticleBuilder.copy(articleView.prepareArticle())
                .withPublishDate(System.currentTimeMillis())
                .build();

        articleService.saveOrUpdate(articleWithPublishDate);

        if(articleWithPublishDate.getArticleId() > 0){
            LOGGER.info("Article with given id: {} was persisted in DB.", articleWithPublishDate.getArticleId());

            modelAndView.addObject(NOTIFICATION.getKey(), "Article was not added. Try again.");
        }else{
            LOGGER.warn("Article was not persisted in DB.");

            modelAndView.addObject(SUCCESS.getKey(), "Article was added.");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/article/edit")
    public ModelAndView edit(@RequestParam Long id){
        ModelAndView modelAndView = new ModelAndView("article/manage");

        final Article article = articleService.find(id);
        if(article == null){
            LOGGER.warn("Article with given id {} was not found in DB.");

            modelAndView.addObject(NOTIFICATION.getKey(), String.format("Article with given id %d was not found.", id));
        }else{
            LOGGER.info("Article with given id {} found in DB.", article);

            Set<Author> authors = authorService.findAll(0, authorService.size().intValue());
            modelAndView.addObject("authors", authors);

            Set<Keyword> keywords = keywordService.findAll(0, keywordService.size().intValue());
            modelAndView.addObject("keywords", keywords);

            ArticleView articleView = new ArticleView(article);
            modelAndView.addObject("articleView", articleView);
        }

        return modelAndView;
    }

    @RequestMapping(value = "/article/edit", method = RequestMethod.POST)
    public ModelAndView edit(@ModelAttribute ArticleView articleView){
        ModelAndView modelAndView = new ModelAndView("article/manage");

        articleService.saveOrUpdate(articleView.prepareArticle());

        return modelAndView;
    }

    @RequestMapping(value = "/article/remove")
    public ModelAndView remove(@RequestParam Long id){
        articleService.remove(id);

        return list();
    }
}
