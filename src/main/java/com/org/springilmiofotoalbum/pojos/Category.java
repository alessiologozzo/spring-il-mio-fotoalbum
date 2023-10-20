package com.org.springilmiofotoalbum.pojos;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Category {

    public static final String STILL_LIFE = "STILL LIFE", STREET_PHOTOGRAPHY = "STREET PHOTOGRAPHY",
            ASTROFOTOGRAFIA = "ASTROFOTOGRAFIA", REPORTAGE = "REPORTAGE", GLAMOUR = "GLAMOUR",
            NATURALISTICA = "NATURALISTICA", VIAGGIO = "VIAGGIO", DOCUMENTARIA = "DOCUMENTARIA",
            SUBACQUEA = "SUBACQUEA", PAESAGGISTICA = "PAESAGGISTICA", SPORTIVA = "SPORTIVA", MACRO = "MACRO",
            MATRIMONIO = "MATRIMONIO", FASHION = "FASHION", ARCHITETTURA = "ARCHITETTURA", RITRATTISTICA = "RITRATTISTICA",
            FOTOGRAFIA_DI_GUERRA = "FOTOGRAFIA DI GUERRA";

    public static final int MIN_LENGTH = 3, MAX_LENGTH = 25;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = MAX_LENGTH)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Photo> photos;

    @SuppressWarnings("unused")
    private Category() {
    }

    public Category(String name) throws IllegalArgumentException {
        setName(name);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException {

        if (name == null)
            throw new IllegalArgumentException("Name cannot be null");
        else if (name.isBlank())
            throw new IllegalArgumentException("Name cannot be blank");
        else if (name.length() < MIN_LENGTH)
            throw new IllegalArgumentException("Name cannnot be smaller than " + MIN_LENGTH);
        else if (name.length() > MAX_LENGTH)
            throw new IllegalArgumentException("Name cannot be greater than " + MAX_LENGTH);
        else
            this.name = name;
    }

}
