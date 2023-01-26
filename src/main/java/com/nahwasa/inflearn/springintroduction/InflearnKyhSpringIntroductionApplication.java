package com.nahwasa.inflearn.springintroduction;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class InflearnKyhSpringIntroductionApplication {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public InflearnKyhSpringIntroductionApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    void checkTable() {
        System.out.println(jdbcTemplate.queryForList("SELECT * FROM MEMBER").get(0));
    }

    public static void main(String[] args) {
        SpringApplication.run(InflearnKyhSpringIntroductionApplication.class, args);
    }

}
