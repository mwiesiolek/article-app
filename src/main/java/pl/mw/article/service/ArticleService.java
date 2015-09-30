package pl.mw.article.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mw.article.dao.ArticleDAO;
import pl.mw.article.dao.AuthorDAO;
import pl.mw.article.dao.KeywordDAO;

/**
 * Created by mwiesiolek on 30/09/2015.
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleDAO articleDAO;

    @Autowired
    private AuthorDAO authorDAO;

    @Autowired
    private KeywordDAO keywordDAO;

}
