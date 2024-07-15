package com.app.shop.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Date;
import java.util.Objects;

public class DanValidator implements ConstraintValidator<DateAfterNow, Date> {

    @Override
    public void initialize(DateAfterNow constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(value)) {
            return false;
        }
        return value.after(new Date());
    }
}
