package pl.mw.article;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ArticleApplication.class)
@WebAppConfiguration
public class ArticleApplicationTests {

    @Test
    public void testAppInitialization() {
        //tests whether or not production beans are initialized properly
    }
}
