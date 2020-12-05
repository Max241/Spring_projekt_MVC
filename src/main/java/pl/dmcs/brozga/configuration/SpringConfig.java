package pl.dmcs.brozga.configuration;



import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import java.util.Locale;

@Configuration
@EnableWebMvc
@ComponentScan("pl.dmcs.brozga")
public class SpringConfig implements WebMvcConfigurer {

    //Configuration - TilesConfig
    @Bean
    public TilesConfigurer tilesConfigurer () {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(new String[]{"tilesConfig/tiles.xml"});
        tilesConfigurer.setCheckRefresh(true);
        return tilesConfigurer;
    }
    //Configure ViewResolver - deliver preffered views

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        TilesViewResolver viewResolver = new TilesViewResolver();
        registry.viewResolver(viewResolver);
    }


//    @Bean
//    public InternalResourceViewResolver viewResolver(){
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/");
//        viewResolver.setSuffix(".jsp");
//        return viewResolver;
//    }



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");

    }



    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("/resources/i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    @Bean
    public LocaleResolver localeResolver(){
            CookieLocaleResolver resolver = new CookieLocaleResolver();
            resolver.setDefaultLocale(new Locale("en"));
            resolver.setCookieName("myLocalCookie");
            resolver.setCookieMaxAge(4800);
            return  resolver;
        }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        registry.addInterceptor(interceptor);
    }
}


