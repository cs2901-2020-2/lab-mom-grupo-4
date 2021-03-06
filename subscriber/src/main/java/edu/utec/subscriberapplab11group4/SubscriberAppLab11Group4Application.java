package edu.utec.subscriberapplab11group4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("controller")
public class SubscriberAppLab11Group4Application {

    public static void main(String[] args) {
        SpringApplication.run(SubscriberAppLab11Group4Application.class, args);
    }

}
