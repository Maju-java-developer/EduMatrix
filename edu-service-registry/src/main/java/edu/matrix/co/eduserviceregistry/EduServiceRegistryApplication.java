package edu.matrix.co.eduserviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EduServiceRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduServiceRegistryApplication.class, args);
        System.out.println("Eureka Server Started!");
    }

}
