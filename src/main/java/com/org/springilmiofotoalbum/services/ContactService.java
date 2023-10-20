package com.org.springilmiofotoalbum.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.springilmiofotoalbum.pojos.Contact;
import com.org.springilmiofotoalbum.repositories.ContactRepository;

@Service
public class ContactService {
    
    @Autowired
    ContactRepository contactRepository;

    public void save(Contact contact) {
        contactRepository.save(contact);
    }
}
