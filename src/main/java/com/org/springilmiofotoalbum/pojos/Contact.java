package com.org.springilmiofotoalbum.pojos;

import com.org.springilmiofotoalbum.dtos.ContactDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Contact {

    public static final int MIN_LENGTH = 3, MAX_EMAIL_LENGTH = 50, MAX_MESSAGE_LENGTH = 255;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = MAX_EMAIL_LENGTH)
    private String email;

    @Column(nullable = false, length = MAX_MESSAGE_LENGTH)
    private String message;

    @SuppressWarnings("unused")
    private Contact() {
    }

    public Contact(String email, String message) throws IllegalArgumentException {
        setEmail(email);
        setMessage(message);
    }

    public Contact(ContactDTO contactDTO) throws IllegalArgumentException {
        setContact(contactDTO);
    }

    public long getId() {
        return id;
    }

    public void setEmail(String email) throws IllegalArgumentException {
        if (email == null)
            throw new IllegalArgumentException("Email cannot be null");
        else if (email.isBlank())
            throw new IllegalArgumentException("Email cannot be blank");
        else if (email.length() < MIN_LENGTH)
            throw new IllegalArgumentException("Email cannnot be smaller than " + MIN_LENGTH);
        else if (email.length() > MAX_EMAIL_LENGTH)
            throw new IllegalArgumentException("Email cannot be greater than " + MAX_EMAIL_LENGTH);
        else
            this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setMessage(String message) throws IllegalArgumentException {
        if (message == null)
            throw new IllegalArgumentException("Message cannot be null");
        else if (message.isBlank())
            throw new IllegalArgumentException("Message cannot be blank");
        else if (message.length() < MIN_LENGTH)
            throw new IllegalArgumentException("Message cannnot be smaller than " + MIN_LENGTH);
        else if (message.length() > MAX_MESSAGE_LENGTH)
            throw new IllegalArgumentException("Message cannot be greater than " + MAX_MESSAGE_LENGTH);
        else
            this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setContact(ContactDTO contactDTO) throws IllegalArgumentException {
        setEmail(contactDTO.getEmail());
        setMessage(contactDTO.getMessage());
    }
}
