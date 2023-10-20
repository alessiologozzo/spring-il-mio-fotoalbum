package com.org.springilmiofotoalbum.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.springilmiofotoalbum.dtos.StringDTO;
import com.org.springilmiofotoalbum.pojos.Photo;
import com.org.springilmiofotoalbum.services.PhotoService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    PhotoService photoService;

    @GetMapping
    public String index(Model model) {
        List<Photo> photos = photoService.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        String photosJSON = "";

        try {
            photosJSON = objectMapper.writeValueAsString(photos);
        } catch (JsonProcessingException e) {
           System.err.println("Error during JSON parsing");
           e.printStackTrace();
        }

        model.addAttribute("photos", photos);
        model.addAttribute("photosJSON", photosJSON);
        model.addAttribute("stringDTO", new StringDTO());

        return "admin/index.html"; 
    }

    @PostMapping
    public String toggleVisibilities(@ModelAttribute StringDTO stringDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Photo> photos = null;
        try {
            photos = objectMapper.readValue(stringDTO.getValue(), new TypeReference<List<Photo>>(){});
            List<Photo> photosToUpdate = new ArrayList<>();

            for(Photo photo: photos) {
                Photo photoToUpdate = photoService.findById(photo.getId());
                photoToUpdate.setVisible(photo.getVisible());
                photosToUpdate.add(photoToUpdate);
            }
            photoService.saveAll(photosToUpdate);
        } catch (Exception e) {
            System.err.println("Error during JSON parsing");
            e.printStackTrace();
        }
        
        return "redirect:/admin";
    }
}
