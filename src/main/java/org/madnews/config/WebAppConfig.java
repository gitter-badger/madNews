package org.madnews.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan("org.madnews")
@EnableSpringDataWebSupport
@EnableJpaRepositories
public class WebAppConfig extends RepositoryRestMvcConfiguration {

    /**
     * Для JSON
     * @return MappingJackson2HttpMessageConverter
     */
    /* Here we register the Hibernate4Module into an ObjectMapper, then set this custom-configured ObjectMapper
     * to the MessageConverter and return it to be added to the HttpMessageConverters of our application*/
    private MappingJackson2HttpMessageConverter jacksonMessageConverter(){
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = new ObjectMapper();
        //Registering Hibernate4Module to support lazy objects
        mapper.registerModule(new Hibernate4Module());
        messageConverter.setObjectMapper(mapper);
        return messageConverter;
    }

    /**
     * Для JSON
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //Here we add our custom-configured HttpMessageConverter
        converters.add(jacksonMessageConverter());
        super.configureMessageConverters(converters);
    }

    /**
     * метод для работы с ресурсами. Можно добавить, какие ссылки контроллер должен игнорировать.
     * Обычно, контроллер при получении ссылки хочет вернуть сервлет или jsp файл.
     * Например, на ссылку "/index" контроллер(LinkController) вернёт наш jsp файл index.jsp.
     * А при ссылке на изображение типа "images/1.jpeg" что ему возвращать? Метод служит для игнора таких ссылок.
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
        registry.addResourceHandler("/media_files/**").addResourceLocations("/media_files/");
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
        registry.addResourceHandler("/partials-html/**").addResourceLocations("/partials-html/");
    }

    /**
     * Сам не разобрался:) Вроде как конструктор ссылок. В контроллере LinkController я возвращаю одно слово "index"/
     * Этот метод приписывает ему префикс "/" и суффикс ".jsp". В результате находится файл index.jsp.
     * @return класс InternalResourceViewResolver
     */
    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }
    
    @Override
    @Bean
    public HateoasPageableHandlerMethodArgumentResolver pageableResolver() {
      HateoasPageableHandlerMethodArgumentResolver resolver = super.pageableResolver();
      resolver.setOneIndexedParameters(true);
      return resolver;
    }
}