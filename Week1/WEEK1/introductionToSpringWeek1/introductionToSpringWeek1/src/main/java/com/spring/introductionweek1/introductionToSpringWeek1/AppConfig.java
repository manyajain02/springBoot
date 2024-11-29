package com.spring.introductionweek1.introductionToSpringWeek1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration   // \Explicit bean declaration : used to define \configuration
public class AppConfig {

    @Bean
    @Scope("prototype") //for prototype two beans were created "  get an apple
//    Creating the apple before use
//    I get an apple
//    Creating the apple before use
            // for prototype obj.hashcode is different for both obj beacuse number of object instance is created for single bean defination in prototype.
//    @Scope("singleton")  // Bean is created ony once
    // for single bean definition only single instance is created
    Apple getApple(){
        System.out.println("I get an apple ");
        return new Apple();
    }
//    Apple getApple(){
//        System.out.println("Iam eating apple in configuration");
//        return new Apple();
//    }
}
