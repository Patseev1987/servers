package ru.patseev.my_gateway_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MyGatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyGatewayServerApplication.class, args);
    }

}
