package com.example.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {
    private boolean onlyNumber = false;

    @Override
    public void initialize(Phone phone) {
        onlyNumber = phone.onlyNumber();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null)
            return false;
        if(onlyNumber)
            return s.matches("[0-9]*");
        else
            return s.matches("[0-9()-]*");
    }
}
