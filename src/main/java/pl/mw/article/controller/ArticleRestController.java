package pl.mw.article.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.mw.article.domain.Article;

/**
 * Created by mwiesiolek on 30/09/2015.
 */
@RestController
@RequestMapping(value = "/rest/article")
public class ArticleRestController {
    private static final Logger LOGGER = LogManager.getLogger(ArticleRestController.class);

    public Article getArticles(@RequestParam Integer start,
                               @RequestParam Integer number,
                               @RequestParam(required = false) String author,
                               @RequestParam(required = false) String startDate,
                               @RequestParam(required = false) String endDate,
                               @RequestParam(required = false) String keyword) {
        return null;
    }
}
