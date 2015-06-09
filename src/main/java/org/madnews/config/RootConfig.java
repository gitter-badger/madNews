package org.madnews.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Класс root настроек. Содержит информацию об источнике данных.  У нас это база данных PostgreSQL
 * метод управление сущностями entityManagerFactory().
 * метод управления транзакциями transactionManager().
 * аннотация @Configuration - объявляем, что этот класс содержит бины для настройки приложения.
 * аннотация @EnableJpaRepositories - включает поддержку Spring Data JPA для запросов к базе.
 * аннотация @EnableTransactionManagement - включает поддержку транзакций.
 */
@Configuration
@EnableTransactionManagement
public class RootConfig {

    /**
     * Метод возвращает класс DataSource с настройками для базы данных (какой драйвер использовать, адрес хоста, логин, пароль).
     * @return класс DataSource
     */
    @Bean
    public DataSource dataSource() {
        return new SimpleDriverDataSource(
                new org.hsqldb.jdbc.JDBCDriver(), "jdbc:hsqldb:file:src/main/data/db", "sa", "");
    }

    /**
     * метод работы с сущностями/объектами (являются все классы, у которых стоит аннотации @Entity, @Service)
     * Используется методом transactionManager()
     * @return класс EntityManagerFactory
     */
    @Bean
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setJpaProperties(additionalProperties());
        factory.setPackagesToScan("org.madnews");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    /**
     * метод работы с транзакциями. Для JPA. Использует метод entityManagerFactory().
     * @return класс PlatformTransactionManager
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

    /**
     * дополнительные настройки для Hibernate. Используется методом entityManagerFactory()
     * @return возвращают класс Properties с настройками
     */
    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql", "false");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        return properties;
    }
}