package com.kodilla.savings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class SavingsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SavingsApplication.class, args);
    }

}
