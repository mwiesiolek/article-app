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
import pl.mw.article.domain.Keyword;
import pl.mw.article.domain.builder.KeywordBuilder;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

/**
 * Created by mwiesiolek on 30/09/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {HibernateConfigurationTest.class, DBServicesConfiguration.class})
@EnableTransactionManagement
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class KeywordDAOTest {

    @Autowired
    private KeywordDAO keywordDAO;

    @Test
    @Transactional
    @Rollback(true)
    public void testSimpleSaveOrUpdate(){

        //given
        final Keyword word = KeywordBuilder.aKeyword()
                .withWord("word")
                .build();

        //when
        keywordDAO.saveOrUpdate(word);

        //then
        assertTrue(word.getKeywordId() > 0);
    }
}