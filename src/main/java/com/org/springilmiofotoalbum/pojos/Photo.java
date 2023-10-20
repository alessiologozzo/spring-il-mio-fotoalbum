package com.org.springilmiofotoalbum.pojos;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.org.springilmiofotoalbum.auth.pojos.User;
import com.org.springilmiofotoalbum.dtos.PhotoDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Photo {
    public static final int MIN_LENGTH = 3, MAX_NAME_LENGTH = 20, MAX_DESCRIPTION_LENGTH = 255, MAX_URL_LENGTH = 255;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = MAX_NAME_LENGTH)
    private String name;

    @Column(nullable = true, length = MAX_DESCRIPTION_LENGTH)
    private String description;

    @Column(nullable = false, length = MAX_URL_LENGTH)
    private String url;

    @Column(nullable = false)
    private boolean visible;

    @ManyToMany
    @JsonIgnore
    private List<Category> categories;

    @ManyToOne
    @JsonIgnore
    private User user;

    @SuppressWarnings("unused")
    private Photo() {
    }

    public Photo(String name, String description, String url, User user, Category... categories) throws IllegalArgumentException {
        setName(name);
        setDescription(description);
        setUrl(url);
        setVisible(true);
        setCategories(Arrays.asList(categories));
        setUser(user);
    }

    public Photo(String name, String description, String url, boolean visible, User user, Category... categories) throws IllegalArgumentException {
        setName(name);
        setDescription(description);
        setUrl(url);
        setVisible(visible);
        setCategories(Arrays.asList(categories));
        setUser(user);
    }

    public Photo(PhotoDTO photoDTO, List<Category> categories, User user) throws IllegalArgumentException {
        setPhoto(photoDTO, categories);
        setUser(user);
        setVisible(true);
    }

    public long getId() {
        return id;
    }

    public void setName(String name) throws IllegalArgumentException {
        if (name == null)
            throw new IllegalArgumentException("Name cannot be null");
        else if (name.isBlank())
            throw new IllegalArgumentException("Name cannot be blank");
        else if (name.length() < MIN_LENGTH)
            throw new IllegalArgumentException("Name cannnot be smaller than " + MIN_LENGTH);
        else if (name.length() > MAX_NAME_LENGTH)
            throw new IllegalArgumentException("Name cannot be greater than " + MAX_NAME_LENGTH);
        else
            this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) throws IllegalArgumentException {
        if (description != null) {
            if (description.isBlank())
                throw new IllegalArgumentException("Description cannot be blank");
            else if (description.length() < MIN_LENGTH)
                throw new IllegalArgumentException("Description cannnot be smaller than " + MIN_LENGTH);
            else if (description.length() > MAX_DESCRIPTION_LENGTH)
                throw new IllegalArgumentException("Description cannot be greater than " + MAX_DESCRIPTION_LENGTH);
        }

        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setUrl(String url) throws IllegalArgumentException {
        if (url == null)
            throw new IllegalArgumentException("Url cannot be null");
        else if (url.isBlank())
            throw new IllegalArgumentException("Url cannot be blank");
        else if (url.length() > MAX_URL_LENGTH)
            throw new IllegalArgumentException("Url cannot be greater than " + MAX_URL_LENGTH);
        else
            this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean getVisible() {
        return visible;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) throws IllegalArgumentException {
        
        if(categories == null || categories.size() < 1)
            throw new IllegalArgumentException("Photo must have at least one category");
        else
            this.categories = categories;
    }

    public void setUser(User user) throws IllegalArgumentException {
        if(user == null)
            throw new IllegalArgumentException("Photo must have one user");
        else
            this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setPhoto(PhotoDTO photoDTO, List<Category> categories) throws IllegalArgumentException {
        setName(photoDTO.getName());
        setDescription(photoDTO.getDescription());
        setUrl(photoDTO.getUrl());
        setCategories(categories);
    }

    public String categoriesToString() {
		String result = "";

		if (categories != null && categories.size() > 0) {
			for (Category category : categories)
				result += category.getName() + ", ";

			result = result.substring(0, result.length() - 2);
		}

		return result;
	}
}
