package edu.matrix.co.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EntityScan("edu.matrix.co.entity")
@SpringBootApplication(exclude = { UserDetailsServiceAutoConfiguration.class })
@ComponentScan(basePackages = {
        "edu.matrix.co.cores",
        "edu.matrix.co.cores.security.config",
        "edu.matrix.co.cores.security.exceptions",
        "edu.matrix.co.security"
})
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
        System.out.println("Security application started");
    }

}
