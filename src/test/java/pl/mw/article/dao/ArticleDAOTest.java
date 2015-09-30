package pl.mw.article.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.mw.article.configuration.DBServicesConfiguration;
import pl.mw.article.configuration.HibernateConfigurationTest;
import pl.mw.article.domain.Article;
import pl.mw.article.domain.builder.ArticleBuilder;

import javax.transaction.Transactional;

import static org.junit.Assert.assertTrue;

/**
 * Created by mwiesiolek on 30/09/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {HibernateConfigurationTest.class, DBServicesConfiguration.class})
@EnableTransactionManagement
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ArticleDAOTest {

    @Autowired
    private ArticleDAO articleDAO;

    @Test
    @Transactional
    @Rollback(true)
    public void testSimpleSaveOrUpdate() {

        //given
        final Article article = ArticleBuilder.anArticle()
                .withDescription("description")
                .withHeader("header")
                .withPublishDate(System.currentTimeMillis())
                .withText("long text")
                .build();

        //when
        articleDAO.saveOrUpdate(article);

        //then
        assertTrue(article.getId() > 0);
    }
}