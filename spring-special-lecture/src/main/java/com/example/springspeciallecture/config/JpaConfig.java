package com.example.springspeciallecture.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = {
                "com.example.springspeciallecture.account.repository",
                "com.example.springspeciallecture.account_profile.repository",
                "com.example.springspeciallecture.board.repository",
                "com.example.springspeciallecture.comment.repository",
                "com.example.springspeciallecture.favorites.repository",
                "com.example.springspeciallecture.subscribe.repository",
                "com.example.springspeciallecture.game_chip.repository"
        },
        entityManagerFactoryRef = "jpaEntityManagerFactory",
        transactionManagerRef = "jpaTransactionManager"
)
public class JpaConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Bean
    public DataSource dataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driverClassName);
        return ds;
    }

    @Bean(name = "jpaEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, DataSource dataSource, JpaProperties jpaProperties) {
        return builder
                .dataSource(dataSource)
                .packages(
                        "com.example.springspeciallecture.account.entity",
                        "com.example.springspeciallecture.account_profile.entity",
                        "com.example.springspeciallecture.board.entity",
                        "com.example.springspeciallecture.comment.entity",
                        "com.example.springspeciallecture.favorites.entity",
                        "com.example.springspeciallecture.subscribe.entity",
                        "com.example.springspeciallecture.game_chip.entity"
                )
                .properties(jpaProperties.getProperties())
                .build();
    }

    @Bean(name = "jpaTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("jpaEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}

