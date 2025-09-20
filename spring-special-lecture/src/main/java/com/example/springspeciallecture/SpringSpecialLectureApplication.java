package com.example.springspeciallecture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
//
//@EnableJpaRepositories(basePackages = "com.example.springspeciallecture.account.repository")
//@EnableR2dbcRepositories(basePackages = "com.example.springspeciallecture.reactive_board.repository")
@SpringBootApplication
public class SpringSpecialLectureApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSpecialLectureApplication.class, args);
    }

}
