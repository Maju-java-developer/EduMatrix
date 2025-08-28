package edu.matrix.co.eduapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EduApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduApiGatewayApplication.class, args);
    }

}
