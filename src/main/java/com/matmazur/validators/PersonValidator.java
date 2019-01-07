package com.matmazur.validators;

import com.matmazur.model.Person;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PersonValidator implements Validator {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        checkEmail(person, errors);
        checkPesel(person, errors);
    }

    private void checkEmail(Person person, Errors errors) {
        if (person.getEmail() != null) {
            errors.rejectValue("email", "required");
        }
        assert person.getEmail() != null;
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(person.getEmail());

        if (!matcher.find()) {
            errors.rejectValue("email", "wrong email pattern");
        }
    }

    private void checkPesel(Person person, Errors errors) {
        String pesel = person.getPesel();

        if (pesel.length() != 8) {
            errors.rejectValue("pesel", "pesel should be 8 digits long");
        }
    }
}
