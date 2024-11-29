package com.spring.introductionweek1.introductionToSpringWeek1;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

// I have used @Component to it as the bean but still same error nullpointerExep why?
//beacuse we have not notififie the spring application that we you noe need to inject the dependency for class Apple , for that e need to use Annotation @Autowired
//@Component
public class Apple {
     public void eatApple(){
        System.out.println("Iam eating apple");
    }

    @PostConstruct
    void callTheseBeforeAppleIsUse(){
        System.out.println("Creating the apple before use");
    }

    @PreDestroy
    void callThisBeforeDestroy(){
        System.out.println("Destroying Apple bean");
    }
}
