package com.org.springilmiofotoalbum.auth.pojos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Role {

    public static final String USER = "USER", ADMIN = "ADMIN";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 255)
    private String name;

	@SuppressWarnings("unused")
	private Role() {
	}

    public Role(String name) {
        setName(name);
    }

    public void setName(String name) {
        switch(name) {
            case USER:
                this.name = USER;
                break;

            case ADMIN:
                this.name = ADMIN;
                break;

            default:
                throw new IllegalArgumentException("Role name not valid.");
        }
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
