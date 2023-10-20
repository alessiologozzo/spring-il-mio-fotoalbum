package com.org.springilmiofotoalbum.auth.pojos;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.org.springilmiofotoalbum.auth.dtos.UserDTO;
import com.org.springilmiofotoalbum.pojos.Photo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class User implements UserDetails {

    public static final int MIN_LENGTH = 3, MAX_LENGTH_USERNAME = 20, MAX_LENGTH_PASSWORD = 50;

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 255)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

	@OneToMany(mappedBy = "user")
	List<Photo> photos;

	@SuppressWarnings("unused")
    private User() {
    }

    public User(String username, String password, Role... roles) throws IllegalArgumentException {
        setUsername(username);
        setPassword(password);
        setRoles(roles);
    }

	public User(UserDTO userDTO, Role... roles) {
		setUsername(userDTO.getUsername());
		setPassword(userDTO.getPassword());
		setRoles(roles);
	}

    public void setUsername(String username) throws IllegalArgumentException {
        if (username == null)
            throw new IllegalArgumentException("Username cannot be null.");
        else if (username.isBlank())
            throw new IllegalArgumentException("Username cannot be blank");
        else if (username.length() < MIN_LENGTH)
            throw new IllegalArgumentException("Username cannot be smaller than " + MIN_LENGTH);
        else if(username.length() > MAX_LENGTH_USERNAME)
            throw new IllegalArgumentException("Username cannot be greater than " + MAX_LENGTH_USERNAME);
        else
            this.username = username;
    }

    public void setPassword(String password) throws IllegalArgumentException {
        if (password == null)
            throw new IllegalArgumentException("Password cannot be null");
        else if (password.isBlank())
            throw new IllegalArgumentException("Password cannot be blank");
        else if (password.length() < MIN_LENGTH)
            throw new IllegalArgumentException("Password cannot be smaller than " + MIN_LENGTH);
        else if(password.length() > MAX_LENGTH_PASSWORD)
            throw new IllegalArgumentException("Password cannot be greater than " + MAX_LENGTH_PASSWORD);
        else {
            final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            this.password = passwordEncoder.encode(password);
        }
    }

    public void setRoles(Role... roles) {
        this.roles = Arrays.asList(roles);
    }

    public List<Role> getRoles() {
        return roles;
    }

    public long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

	public List<Photo> getPhotos() {
		return photos;
	}
}
