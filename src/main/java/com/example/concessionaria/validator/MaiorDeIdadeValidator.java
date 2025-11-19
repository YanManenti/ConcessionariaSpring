package com.example.concessionaria.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MaiorDeIdadeValidator implements ConstraintValidator<MaiorDeIdade, Integer> {
    @Override
    public boolean isValid(Integer idade, ConstraintValidatorContext constraintValidatorContext) {
        return idade >= 18;
    }
}
