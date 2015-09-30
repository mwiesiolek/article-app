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
    public ArticleDAO createArticleDAO(){
        return new ArticleDAO();
    }

    @Bean
    public AuthorDAO createAuthorDAO(){
        return new AuthorDAO();
    }

    @Bean
    public KeywordDAO createKeywordDAO(){
        return new KeywordDAO();
    }

    @Bean
    public ArticleService createArticleService(){
        return new ArticleService();
    }
}
