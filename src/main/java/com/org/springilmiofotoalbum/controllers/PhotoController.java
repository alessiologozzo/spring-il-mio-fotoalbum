package com.org.springilmiofotoalbum.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.springilmiofotoalbum.auth.pojos.Role;
import com.org.springilmiofotoalbum.auth.pojos.User;
import com.org.springilmiofotoalbum.dtos.PhotoDTO;
import com.org.springilmiofotoalbum.pojos.Category;
import com.org.springilmiofotoalbum.pojos.Photo;
import com.org.springilmiofotoalbum.services.CategoryService;
import com.org.springilmiofotoalbum.services.PhotoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/photos")
public class PhotoController {
    
    @Autowired
    PhotoService photoService;

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String index(Authentication authentication, Model model, @RequestParam(required = false) String name) {
        User user = (User)authentication.getPrincipal();
        List<Photo> photos = null;
        boolean empty = true;

        if(name == null) {
            photos = photoService.findByUserId(user.getId());
            name = "";
        }
        else {
            final String fName = name.toLowerCase();
            photos = photoService.findByUserId(user.getId()).stream().filter(photo -> photo.getName().toLowerCase().contains(fName)).toList();
        }

        if(photos != null) {
            for(int i = 0; i < photos.size() && empty; i++)
                if(photos.get(i).getVisible())
                    empty = false;
        }

        model.addAttribute("photos", photos);
        model.addAttribute("name", name);
        model.addAttribute("empty", empty);

        return "photo/index.html";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable long id, Model model, Authentication authentication) {
        String result = "redirect:/forbidden";
        User user = (User)authentication.getPrincipal();
        boolean isAdmin = isAdmin(user);

        Photo photo = photoService.findById(id);
        if(photo.getUser().getId() == user.getId()) {
            result = "photo/show.html";
            model.addAttribute("photo", photo);
            model.addAttribute("canChange", true);
        }
        else if(isAdmin) {
            result = "photo/show.html";
            model.addAttribute("photo", photo);
            model.addAttribute("canChange", false);
        }

        return result;
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<Category> categories = categoryService.findAll();

        model.addAttribute("photoDTO", new PhotoDTO());
        model.addAttribute("categories", categories);

        return "photo/create.html";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute PhotoDTO photoDTO, BindingResult bindingResult, Model model, Authentication authentication) {
        String result = "";
        Photo photo = null;

        if(!bindingResult.hasErrors()) {
            List<Category> categories = new ArrayList<>();
            List<String> categoryNames = photoDTO.getCategoryNames();

            for(String categoryName: categoryNames)
                categories.add(categoryService.findByName(categoryName));

            photo = new Photo(photoDTO, categories, (User)authentication.getPrincipal());
            photoService.save(photo);
            result = "redirect:/photos/" + photo.getId();
        }
        else {
            List<Category> categories = categoryService.findAll();
            model.addAttribute("categories", categories);
            result = "photo/create.html";
        }

        return result;
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable long id, Model model, Authentication authentication) {
        String result = "redirect:/forbidden";
        User user = (User)authentication.getPrincipal();

        Photo photo = photoService.findById(id);
        if(photo.getUser().getId() == user.getId()) {
            result = "photo/show.html";
            List<Category> categories = categoryService.findAll();
            model.addAttribute("photoDTO", new PhotoDTO(photo));
            model.addAttribute("categories", categories);
        }

        return result;
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute PhotoDTO photoDTO, BindingResult bindingResult, @PathVariable long id, Model model) {
        String result = "photo/edit.html";

        if(!bindingResult.hasErrors()) {
            Photo photo = photoService.findById(id);

            List<Category> categories = new ArrayList<>();
            List<String> categoryNames = photoDTO.getCategoryNames();

            for(String categoryName: categoryNames)
                categories.add(categoryService.findByName(categoryName));

            photo.setPhoto(photoDTO, categories);
            photoService.save(photo);

            result = "redirect:/photos/" + photo.getId();
        }
        else {
            List<Category> categories = categoryService.findAll();
            model.addAttribute("categories", categories);
        }

        return result;
    }

    @PostMapping("/delete/{id}")
    public String delete(Authentication authentication, @PathVariable long id) {
        photoService.deleteById(id);

        return "redirect:/photos";
    }

    private boolean isAdmin(User user) {
        boolean isAdmin = false;
        List<Role> roles = user.getRoles();

        for(int i = 0; i < roles.size() && !isAdmin; i++)
            if(roles.get(i).getName().equals(Role.ADMIN))
                isAdmin = true;

        return isAdmin;
    }
}
