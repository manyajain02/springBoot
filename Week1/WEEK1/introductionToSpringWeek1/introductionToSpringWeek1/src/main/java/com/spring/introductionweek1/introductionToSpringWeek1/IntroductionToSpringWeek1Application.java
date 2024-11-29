 package com.spring.introductionweek1.introductionToSpringWeek1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IntroductionToSpringWeek1Application implements CommandLineRunner {
		@Autowired   // we need to use this annotation to notifify the spring that you need to inject dependency for the bean ,
		// it will notify spring framework that this object should be injected whenever required
		Apple obj;



		@Autowired
		Apple obj2;
	public static void main(String[] args) {
		SpringApplication.run(IntroductionToSpringWeek1Application.class, args);


	}

	@Override
	public void run(String... args) throws Exception {
		obj.eatApple();   // will give NullpointerException because i have not make class Apple as the bean yet , to make Apple class as a Bean use @Component Annontation
		obj2.eatApple(); //even after creating two obj , getting " Creating the apple before use
		System.out.println(obj.hashCode());
		System.out.println(obj2.hashCode());
	}
}
