package pl.mw.article.controller;

import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.mw.article.configuration.ControllerConfigurationTest;
import pl.mw.article.configuration.DBServicesConfiguration;
import pl.mw.article.configuration.HibernateConfigurationTest;
import pl.mw.article.dao.ArticleDAO;
import pl.mw.article.domain.Article;
import pl.mw.article.domain.Author;
import pl.mw.article.domain.Keyword;
import pl.mw.article.domain.builder.ArticleBuilder;
import pl.mw.article.domain.builder.AuthorBuilder;
import pl.mw.article.domain.builder.KeywordBuilder;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by mwiesiolek on 01/10/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {HibernateConfigurationTest.class, DBServicesConfiguration.class, ControllerConfigurationTest.class})
@EnableTransactionManagement
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ArticleRestControllerTest {
    final FastDateFormat dateFormatter = FastDateFormat.getInstance("yyyy-MM-dd");

    @Autowired
    private ArticleRestController articleRestController;

    @Autowired
    private ArticleDAO articleDAO;

    @Test
    @Transactional
    @Rollback(true)
    public void testSimpleCase() throws ParseException {

        //given
        generateArticles();

        //when
        final Set<Article> articles = articleRestController.getArticles(0, 5, null, null, null, null, null);

        //then
        assertEquals(5, articles.size());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testWithSurname() throws ParseException {

        //given
        generateArticles();

        //when
        final Set<Article> articles = articleRestController.getArticles(0, 5, null, "surname", null, null, null);

        //then
        assertEquals(5, articles.size());
    }

    private void generateArticles() throws ParseException {

        int amount = 20;
        for (int i = 0; i < amount; i++) {
            final Article article = ArticleBuilder.anArticle()
                    .withDescription(String.format("description%d", i))
                    .withHeader(String.format("header%d", i))
                    .withPublishDate(dateFormatter.parse("2015-10-01").getTime())
                    .withText(String.format("longtext%d", i))
                    .build();

            final Author author = AuthorBuilder.anAuthor()
                    .withSurname(String.format("surname%d", i))
                    .withFirstName(String.format("firstName%d", i))
                    .build();

            final Keyword word = KeywordBuilder.aKeyword()
                    .withWord(String.format("word%d", i))
                    .build();

            article.addAuthor(author);
            article.addKeyword(word);

            articleDAO.saveOrUpdate(article);
        }
    }
}