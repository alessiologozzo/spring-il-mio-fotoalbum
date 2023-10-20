package com.org.springilmiofotoalbum.auth.validators.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.org.springilmiofotoalbum.auth.validators.UsernameValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.TYPE}) 
@Retention(RetentionPolicy.RUNTIME) 
@Constraint(validatedBy=UsernameValidator.class) 
public @interface UniqueUsername {
    String message() default "Nome già in uso.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}