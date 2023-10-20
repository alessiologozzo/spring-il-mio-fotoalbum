package com.org.springilmiofotoalbum.validators.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.org.springilmiofotoalbum.validators.CategoryValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.TYPE}) 
@Retention(RetentionPolicy.RUNTIME) 
@Constraint(validatedBy=CategoryValidator.class) 
public @interface AtLeastOneCategory {
    String message() default "La foto deve avere almeno una categoria.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}