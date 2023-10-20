package com.org.springilmiofotoalbum.auth.validators;

import org.springframework.beans.factory.annotation.Autowired;

import com.org.springilmiofotoalbum.auth.dtos.UserDTO;
import com.org.springilmiofotoalbum.auth.services.UserService;
import com.org.springilmiofotoalbum.auth.validators.annotations.UniqueUsername;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<UniqueUsername, UserDTO>{

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(UserDTO userDTO, ConstraintValidatorContext context) {
        boolean result = true;

        if(userService.findByUsername(userDTO.getUsername()) != null)
            result = false;

        return result;
    }
    
}
