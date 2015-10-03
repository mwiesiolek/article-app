package pl.mw.article.dao;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
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
import pl.mw.article.domain.Author;
import pl.mw.article.domain.Keyword;
import pl.mw.article.domain.builder.ArticleBuilder;
import pl.mw.article.domain.builder.AuthorBuilder;
import pl.mw.article.domain.builder.KeywordBuilder;

import javax.transaction.Transactional;

import java.util.Set;

import static org.junit.Assert.*;

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
        assertTrue(article.getArticleId() > 0);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testComplexSaveOrUpdate() {

        //given
        final Article article = ArticleBuilder.anArticle()
                .withDescription("description")
                .withHeader("header")
                .withPublishDate(System.currentTimeMillis())
                .withText("long text")
                .withArticleId(Long.valueOf(0))
                .build();

        final Author author = AuthorBuilder.anAuthor()
                .withSurname("surname")
                .withFirstName("firstName")
                .withAuthorId(Long.valueOf(0))
                .build();

        final Keyword word = KeywordBuilder.aKeyword()
                .withWord("word")
                .withKeywordId(Long.valueOf(0))
                .build();

        article.addAuthor(author);
        article.addKeyword(word);

        //when
        articleDAO.saveOrUpdate(article);
        articleDAO.flush();
        final Article fromDB = articleDAO.find(article.getArticleId());

        //then
        assertEquals(article, fromDB);
        assertEquals(article.getAuthors().size(), fromDB.getAuthors().size());
        assertEquals(article.getKeywords().size(), fromDB.getKeywords().size());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testFindAll() {

        //given
        final Article article1 = ArticleBuilder.anArticle()
                .withDescription("description")
                .withHeader("header")
                .withPublishDate(System.currentTimeMillis())
                .withText("long text")
                .build();

        final Article article2 = ArticleBuilder.anArticle()
                .withDescription("description")
                .withHeader("header")
                .withPublishDate(System.currentTimeMillis())
                .withText("long text")
                .build();

        //when
        articleDAO.saveOrUpdate(article1);
        articleDAO.saveOrUpdate(article2);
        final Set<Article> articles = articleDAO.findAll(0, 2);

        //then
        assertEquals(2, articles.size());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testFindAllWithEmptyDB() {

        //when
        final Set<Article> articles = articleDAO.findAll(0, 2);

        //then
        assertEquals(0, articles.size());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testFindAllWithCriteria() {

        //given
        final Article article1 = ArticleBuilder.anArticle()
                .withDescription("description")
                .withHeader("header")
                .withPublishDate(System.currentTimeMillis())
                .withText("long text")
                .build();

        final Article article2 = ArticleBuilder.anArticle()
                .withDescription("description")
                .withHeader("header")
                .withPublishDate(System.currentTimeMillis())
                .withText("long text")
                .build();

        Criterion criterion = Restrictions.eq("header", "header");

        //when
        articleDAO.saveOrUpdate(article1);
        articleDAO.saveOrUpdate(article2);
        final Set<Article> articles = articleDAO.findAllWith(criterion);

        //then
        assertEquals(2, articles.size());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testFindAllWithCriteriaInRange() {

        //given
        final Article article1 = ArticleBuilder.anArticle()
                .withDescription("description")
                .withHeader("header")
                .withPublishDate(System.currentTimeMillis())
                .withText("long text")
                .build();

        final Article article2 = ArticleBuilder.anArticle()
                .withDescription("description")
                .withHeader("header")
                .withPublishDate(System.currentTimeMillis())
                .withText("long text")
                .build();

        Criterion criterion = Restrictions.eq("header", "header");

        //when
        articleDAO.saveOrUpdate(article1);
        articleDAO.saveOrUpdate(article2);
        final Set<Article> articles = articleDAO.findAllWith(criterion, 0, 2);

        //then
        assertEquals(2, articles.size());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testDelete() {

        //given
        final Article article = ArticleBuilder.anArticle()
                .withDescription("description")
                .withHeader("header")
                .withPublishDate(System.currentTimeMillis())
                .withText("long text")
                .build();

        //when
        articleDAO.saveOrUpdate(article);
        articleDAO.delete(article);
        final Article fromDB = articleDAO.find(article.getArticleId());

        //then
        assertNull(fromDB);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testSize() {

        //given
        final Article article1 = ArticleBuilder.anArticle()
                .withDescription("description")
                .withHeader("header")
                .withPublishDate(System.currentTimeMillis())
                .withText("long text")
                .build();

        final Article article2 = ArticleBuilder.anArticle()
                .withDescription("description")
                .withHeader("header")
                .withPublishDate(System.currentTimeMillis())
                .withText("long text")
                .build();

        //when
        articleDAO.saveOrUpdate(article1);
        articleDAO.saveOrUpdate(article2);
        final Long size = articleDAO.size();

        //then
        assertEquals(Long.valueOf(2), size);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testSizeWithCriteria() {

        //given
        final Article article1 = ArticleBuilder.anArticle()
                .withDescription("description")
                .withHeader("header1")
                .withPublishDate(System.currentTimeMillis())
                .withText("long text")
                .build();

        final Article article2 = ArticleBuilder.anArticle()
                .withDescription("description")
                .withHeader("header2")
                .withPublishDate(System.currentTimeMillis())
                .withText("long text")
                .build();

        Criterion criterion = Restrictions.eq("header", "header1");

        //when
        articleDAO.saveOrUpdate(article1);
        articleDAO.saveOrUpdate(article2);
        final Long size = articleDAO.size(criterion);

        //then
        assertEquals(Long.valueOf(1), size);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void shouldExist() {

        //given
        final Article article = ArticleBuilder.anArticle()
                .withDescription("description")
                .withHeader("header")
                .withPublishDate(System.currentTimeMillis())
                .withText("long text")
                .build();

        Criterion criterion = Restrictions.eq("header", "header");

        //when
        articleDAO.saveOrUpdate(article);
        final boolean result = articleDAO.checkIfExist(criterion);

        //then
        assertTrue(result);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void shouldNotExist() {

        //given
        final Article article = ArticleBuilder.anArticle()
                .withDescription("description")
                .withHeader("header")
                .withPublishDate(System.currentTimeMillis())
                .withText("long text")
                .build();

        Criterion criterion = Restrictions.eq("header", "header1");

        //when
        articleDAO.saveOrUpdate(article);
        final boolean result = articleDAO.checkIfExist(criterion);

        //then
        assertFalse(result);
    }
}