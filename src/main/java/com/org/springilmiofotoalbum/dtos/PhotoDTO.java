package com.org.springilmiofotoalbum.dtos;

import java.util.ArrayList;
import java.util.List;

import com.org.springilmiofotoalbum.pojos.Photo;
import com.org.springilmiofotoalbum.validators.annotations.AtLeastOneCategory;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@AtLeastOneCategory
public class PhotoDTO {

    @NotBlank
    @Size(min = Photo.MIN_LENGTH, max = Photo.MAX_NAME_LENGTH)
    private String name;

    @Nullable
    @Size(min = Photo.MIN_LENGTH, max = Photo.MAX_DESCRIPTION_LENGTH)
    private String description;

    @NotBlank
    @Size(max = Photo.MAX_URL_LENGTH)
    private String url;

    private long id;

    private String categoriesStr;

    public PhotoDTO() {
    }

    public PhotoDTO(Photo photo) {
        setName(photo.getName());
        setDescription(photo.getDescription());
        setUrl(photo.getUrl());
        setId(photo.getId());
        setCategoriesStr(photo.categoriesToString());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description != null && description.equals(""))
            this.description = null;
        else
            this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoriesStr() {
        return categoriesStr;
    }

    public void setCategoriesStr(String categoriesStr) {
        this.categoriesStr = categoriesStr;
    }

    public List<String> getCategoryNames() {
            List<String> categoryNames = new ArrayList<>();
			String[] categoriesStrL = categoriesStr.split(",");

			if (categoriesStrL.length == 1 && categoriesStrL[0].isBlank()) {
				categoryNames = null;
				categoriesStrL = new String[0];
			}
			for (int i = 0; i < categoriesStrL.length; i++)
                categoryNames.add(categoriesStrL[i].strip());

            return categoryNames;
    }
}
