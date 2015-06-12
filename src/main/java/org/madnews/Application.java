package org.madnews;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("org.madnews")
@EnableSpringDataWebSupport
@EnableJpaRepositories("org.madnews.repository")
public class Application extends RepositoryRestMvcConfiguration {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private MappingJackson2HttpMessageConverter jacksonMessageConverter(){
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = new ObjectMapper();
        //Registering Hibernate4Module to support lazy objects
        mapper.registerModule(new Hibernate4Module());
        messageConverter.setObjectMapper(mapper);
        return messageConverter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //Here we add our custom-configured HttpMessageConverter
        converters.add(jacksonMessageConverter());
        super.configureMessageConverters(converters);
    }


    @Bean
    public DataSource dataSource() {
        return new SimpleDriverDataSource(
                new org.hsqldb.jdbc.JDBCDriver(), "jdbc:hsqldb:file:src/main/data/db", "sa", "");
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.setPackagesToScan("org.madnews.entity");
        factory.setDataSource(dataSource());
        factory.setJpaPropertyMap(new HashMap<String, Object>() {{
            put("hibernate.hbm2ddl.auto", "update");
        }});
        factory.afterPropertiesSet();
        return factory;
    }
}
