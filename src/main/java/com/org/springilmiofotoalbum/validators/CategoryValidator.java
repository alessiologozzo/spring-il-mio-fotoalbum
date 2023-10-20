package com.org.springilmiofotoalbum.validators;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.org.springilmiofotoalbum.dtos.PhotoDTO;
import com.org.springilmiofotoalbum.pojos.Category;
import com.org.springilmiofotoalbum.services.CategoryService;
import com.org.springilmiofotoalbum.validators.annotations.AtLeastOneCategory;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CategoryValidator implements ConstraintValidator<AtLeastOneCategory, PhotoDTO>{

    @Autowired
    private CategoryService categoryService;

    @Override
    public boolean isValid(PhotoDTO photoDTO, ConstraintValidatorContext context) {
        boolean result = false;

        List<String> categoryNames = photoDTO.getCategoryNames();
        if(categoryNames != null)
        for(int i = 0; i < categoryNames.size() && !result; i++) {
            Category category = categoryService.findByName(categoryNames.get(i));
            if(category != null)
                result = true;
        }

        return result;
    }
    
}
