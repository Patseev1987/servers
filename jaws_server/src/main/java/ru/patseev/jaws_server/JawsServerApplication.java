package ru.patseev.jaws_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class JawsServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JawsServerApplication.class, args);
    }

}
