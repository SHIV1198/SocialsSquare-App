package com.socials.square;

import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;


@Testcontainers
public class AbstractTestContainer {

    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("test_posts")
            .withUsername("postgres")
            .withPassword("password")
            //.withEnv("TZ", "UTC")
            .withInitScript("schema.sql");

    @DynamicPropertySource
    public static void registerProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", () -> postgres.getJdbcUrl());
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }


    @Test
    void isContainerRunning() {
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }

    private static DataSource getDataSource(){
        return DataSourceBuilder.create()
                .driverClassName(postgres.getDriverClassName())
                .url(postgres.getJdbcUrl())
                .username(postgres.getUsername())
                .password(postgres.getPassword())
                .build();
    }

    protected static JdbcTemplate getJdbcTemplate(){
        return  new JdbcTemplate(getDataSource());
    }
}