package org.madnews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@EnableSpringDataWebSupport
@EnableJpaRepositories("org.madnews.repository")
public class Application{

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
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