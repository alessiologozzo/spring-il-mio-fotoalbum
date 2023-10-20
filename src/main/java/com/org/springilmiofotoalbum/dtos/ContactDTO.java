package com.org.springilmiofotoalbum.dtos;

import com.org.springilmiofotoalbum.pojos.Contact;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ContactDTO {

    @Email
    @Size(min = Contact.MIN_LENGTH, max = Contact.MAX_EMAIL_LENGTH)
    private String email;

    @NotBlank
    @Size(min = Contact.MIN_LENGTH, max = Contact.MAX_MESSAGE_LENGTH)
    private String message;

    public ContactDTO() {
    }

    public ContactDTO(String email, String message) {
        setEmail(email);
        setMessage(message);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
