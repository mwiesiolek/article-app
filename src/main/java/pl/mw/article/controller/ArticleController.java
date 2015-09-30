package pl.mw.article.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mwiesiolek on 30/09/2015.
 */
@Controller
@RequestMapping(value = "//article")
public class ArticleController {
    private static final Logger LOGGER = LogManager.getLogger(ArticleController.class);

    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView("article/list");

        return modelAndView;
    }

    public ModelAndView add(){
        ModelAndView modelAndView = new ModelAndView("article/manage");

        return modelAndView;
    }

    public ModelAndView edit(){
        ModelAndView modelAndView = new ModelAndView("article/manage");

        return modelAndView;
    }
}
