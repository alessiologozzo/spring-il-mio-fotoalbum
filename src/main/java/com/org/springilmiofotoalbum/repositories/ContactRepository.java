package com.org.springilmiofotoalbum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.springilmiofotoalbum.pojos.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
