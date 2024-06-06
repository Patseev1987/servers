package ru.patseev.transactionsserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;


@SpringBootApplication
public class TransactionsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionsServerApplication.class, args);
    }

}
