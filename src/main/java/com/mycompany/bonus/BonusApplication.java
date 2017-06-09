package com.mycompany.bonus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Spring boot initialization class
 */
@Configuration
@ComponentScan(basePackages = {"com.mycompany.bonus"})
@EnableAutoConfiguration
@PropertySources({@PropertySource("classpath:application.properties")})
public class BonusApplication {

    public static void main(final String[] args) {
        SpringApplication.run(BonusApplication.class, args);
    }


}
