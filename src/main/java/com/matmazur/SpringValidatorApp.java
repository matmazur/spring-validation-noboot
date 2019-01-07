package com.matmazur;

import com.matmazur.model.Person;
import com.matmazur.services.PersonValidationService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@ComponentScan
public class SpringValidatorApp {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringValidatorApp.class);

        PersonValidationService service = context.getBean(PersonValidationService.class);
        Person person1 = new Person("Rick", "Jimeneez", 5, "654465", "");
        Person person2 = new Person("Mike", "Right", 543, "12345678", "mike@gmail.pl");
        Person person3 = new Person("Buck", "semiright", 1235, "65664465", "buck@gmail");

        service.addPerson(person1);
        service.addPerson(person2);
        service.addPerson(person3);
        System.out.println(service.getPeople());
        context.close();
    }

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }
}
