package com.app.shop.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

public class DobValidator implements ConstraintValidator<DateOfBirth, Date> {
    private int min;

    @Override
    public void initialize(DateOfBirth constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) {
            return false;
        }

        LocalDate dob = value.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long years = ChronoUnit.YEARS.between(dob, LocalDate.now());

        return years >= min;
    }
}
