package pl.mw.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pl.mw.article.event.ApplicationStartedListener;

@SpringBootApplication
@EnableTransactionManagement
@EnableWebSecurity
public class ArticleApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ArticleApplication.class, args);

        ApplicationStartedListener bean = run.getBean(ApplicationStartedListener.class);
        bean.onApplicationEvent(null);
    }
}
