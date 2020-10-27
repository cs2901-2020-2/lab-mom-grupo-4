package edu.utec.publisherapplab11group4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("controller")
public class PublisherAppLab11Group4Application {

    public static void main(String[] args) {
        SpringApplication.run(PublisherAppLab11Group4Application.class, args);
    }

}
