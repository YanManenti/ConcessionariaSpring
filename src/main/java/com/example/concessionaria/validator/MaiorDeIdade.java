package com.example.concessionaria.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MaiorDeIdadeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MaiorDeIdade {
    String message() default "O usuário deve ser maior de idade";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};
}
