package pl.mw.article.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import pl.mw.article.interceptor.ViewInterceptor;

/**
 * Created by mwiesiolek on 30/09/2015.
 */
@Configuration
@ComponentScan(basePackages = {"pl.mw.article"})
public class WebConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private ViewInterceptor viewInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/fonts/**")
                .addResourceLocations("classpath:/static/fonts/");
        registry.addResourceHandler("/img/**")
                .addResourceLocations("classpath:/static/img/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(viewInterceptor);
    }

    @Bean(name = "freeMarkerViewResolver")
    public ViewResolver getViewResolver(FreeMarkerConfigurer freeMarkerConfigurer) {
        freeMarkerConfigurer.setDefaultEncoding("UTF-8");

        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(false);
        resolver.setContentType("text/html;charset=UTF-8");
        resolver.setSuffix(".ftl");

        return resolver;

    }

    @Bean
    public ViewInterceptor getViewInterceptor(){
        return new ViewInterceptor();
    }
}
