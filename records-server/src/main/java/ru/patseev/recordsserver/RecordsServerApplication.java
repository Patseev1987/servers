package ru.patseev.recordsserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
public class RecordsServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecordsServerApplication.class, args);
	}

}
