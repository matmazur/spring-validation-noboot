package com.matmazur.services;

import com.matmazur.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;


import java.util.HashSet;
import java.util.Set;

@Service
public class PersonValidationService {

    private Set<Person> people;
    private Validator validator;

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
            for(ObjectError err: errors.getAllErrors()) {
                System.err.println(err.getDefaultMessage());
            }
        }
    }

    public Set<Person> getPeople() {
        return people;
    }
}
