package pl.mw.article.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.mw.article.dao.ArticleDAO;
import pl.mw.article.dao.AuthorDAO;
import pl.mw.article.dao.KeywordDAO;
import pl.mw.article.service.ArticleService;

/**
 * Created by mwiesiolek on 30/09/2015.
 */
@Configuration
public class DBServicesConfiguration {

    @Bean
    public ArticleDAO createArticleDAO() {
        final ArticleDAO articleDAO = new ArticleDAO();
        return articleDAO;
    }

    @Bean
    public AuthorDAO createAuthorDAO() {
        final AuthorDAO authorDAO = new AuthorDAO();
        return authorDAO;
    }

    @Bean
    public KeywordDAO createKeywordDAO() {
        final KeywordDAO keywordDAO = new KeywordDAO();
        return keywordDAO;
    }

    @Bean
    public ArticleService createArticleService() {
        return new ArticleService();
    }
}
