package com.org.springilmiofotoalbum.auth.dtos;

import com.org.springilmiofotoalbum.auth.pojos.User;
import com.org.springilmiofotoalbum.auth.validators.annotations.UniqueUsername;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@UniqueUsername
public class UserDTO {
    
    @NotBlank
    @Size(min = User.MIN_LENGTH, max = User.MAX_LENGTH_USERNAME)
    private String username;

    @NotBlank
    @Size(min = User.MIN_LENGTH, max = User.MAX_LENGTH_PASSWORD)
    private String password;

    public UserDTO() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
