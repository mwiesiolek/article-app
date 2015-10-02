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
import pl.mw.article.service.ArticleService;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.Collections;
import java.util.Set;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;

/**
 * Created by mwiesiolek on 01/10/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {HibernateConfigurationTest.class, DBServicesConfiguration.class, ControllerConfigurationTest.class})
@EnableTransactionManagement
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class ArticleRestControllerTest {
    final FastDateFormat dateFormatter = FastDateFormat.getInstance("yyyy-MM-dd", TimeZone.getTimeZone("GMT"));

    @Autowired
    private ArticleRestController articleRestController;

    @Autowired
    private ArticleService articleService;

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
        final int expected = 5;
        generateArticles();

        //when
        final Set<Article> articles = articleRestController.getArticles(0, expected, null, "sur", null, null, null);

        //then
        assertEquals(expected, articles.size());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testWithFirstname() throws ParseException {

        //given
        final int expected = 5;
        generateArticles();

        //when
        final Set<Article> articles = articleRestController.getArticles(0, expected, "first", null, null, null, null);

        //then
        assertEquals(expected, articles.size());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testWithFirstnameAndSurname() throws ParseException {

        //given
        final int expected = 5;
        generateArticles();

        //when
        final Set<Article> articles = articleRestController.getArticles(0, expected, "first", "sur", null, null, null);

        //then
        assertEquals(expected, articles.size());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testWithStartDate() throws ParseException {

        //given
        final int expected = 5;
        generateArticles();

        //when
        final Set<Article> articles = articleRestController.getArticles(0, expected, null, "sur", dateFormatter.parse("2015-09-10").getTime(), null, null);

        //then
        assertEquals(expected, articles.size());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testWithEndDate() throws ParseException {

        //given
        final int expected = 5;
        generateArticles();

        //when
        final Set<Article> articles = articleRestController.getArticles(0, expected, null, "sur", null, dateFormatter.parse("2015-10-10").getTime(), null);

        //then
        assertEquals(expected, articles.size());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testInBetweenStartAndEndDate() throws ParseException {

        //given
        final int expected = 5;
        generateArticles();

        //when
        final Set<Article> articles = articleRestController.getArticles(0, expected, null, "sur", dateFormatter.parse("2015-09-10").getTime(), dateFormatter.parse("2015-10-10").getTime(), null);

        //then
        assertEquals(expected, articles.size());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testWithKeyword() throws ParseException {

        //given
        final int expected = 5;
        generateArticles();

        //when
        final Set<Article> articles = articleRestController.getArticles(0, expected, null, null, null, null, "word");

        //then
        assertEquals(expected, articles.size());
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

            articleService.addWithJoins(article, Collections.singleton(author), Collections.singleton(word));
        }
    }
}