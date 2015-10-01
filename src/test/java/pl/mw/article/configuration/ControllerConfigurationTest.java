package pl.mw.article.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mwiesiolek on 01/10/2015.
 */
@Configuration
@ComponentScan(basePackages = {"pl.mw.article.controller"})
public class ControllerConfigurationTest {
}
