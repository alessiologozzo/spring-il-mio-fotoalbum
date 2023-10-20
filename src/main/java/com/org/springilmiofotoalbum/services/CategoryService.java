package com.org.springilmiofotoalbum.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.org.springilmiofotoalbum.pojos.Category;
import com.org.springilmiofotoalbum.repositories.CategoryRepository;

@Service
@Lazy
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public void saveAll(List<Category> categories) {
        categoryRepository.saveAll(categories);
    }

    public Category findById(long id) {
        Category category = null;
        Optional<Category> oCategory = categoryRepository.findById(id);

        if(oCategory.isPresent())
            category = oCategory.get();

        return category;
    }

    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
