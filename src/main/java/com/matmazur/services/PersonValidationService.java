package com.matmazur.services;

import com.matmazur.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import java.util.HashSet;
import java.util.Set;

@Service
@PropertySource("classpath:error_messages.properties")
public class PersonValidationService {

    private Set<Person> people;
    private Validator validator;

    @Autowired
    Environment env;

    @Autowired
    public PersonValidationService(Validator validator) {
        this.people = new HashSet<>();
        this.validator = validator;
    }

    public void addPerson(Person person) {

        Errors errors = new BeanPropertyBindingResult(person, "person");
        validator.validate(person, errors);
        if (!errors.hasErrors()) {
            people.add(person);
        } else {
            System.err.printf("There are errors(%d):\n", errors.getErrorCount());
            for (ObjectError v : errors.getAllErrors()) {
                System.err.println(env.getProperty(v.getCode()));
            }
        }
    }

    public Set<Person> getPeople() {
        return people;
    }
}
