package com.example.concessionaria.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UniqueValueValidator.class)
public @interface UniqueValue {

    String message() default "O valor já existe no banco de dados";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String fieldName();   // nome do campo na entidade
    Class<?> domainClass(); // entidade (ex: User.class, Automovel.class)
}
