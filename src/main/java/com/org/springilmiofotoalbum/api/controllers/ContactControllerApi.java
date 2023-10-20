package com.org.springilmiofotoalbum.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.springilmiofotoalbum.dtos.ContactDTO;
import com.org.springilmiofotoalbum.pojos.Contact;
import com.org.springilmiofotoalbum.services.ContactService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/contacts")
public class ContactControllerApi {
    
    @Autowired
    ContactService contactService;

    @PostMapping
    public ResponseEntity<Boolean> store(@Valid @RequestBody ContactDTO contactDTO, BindingResult bindingResult) {
        ResponseEntity<Boolean> result = null;


        
        if(bindingResult.hasErrors())
            result = new ResponseEntity<Boolean>(false, HttpStatus.NOT_ACCEPTABLE);
        else {
            result = new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
            Contact contact = new Contact(contactDTO);
            contactService.save(contact);
        }

        return result;
    }
}
