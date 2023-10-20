package com.org.springilmiofotoalbum.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.springilmiofotoalbum.pojos.Photo;
import com.org.springilmiofotoalbum.services.PhotoService;

@Controller
@CrossOrigin
@RequestMapping("/api/photos")
public class PhotoControllerApi {
    
    @Autowired
    PhotoService photoService;

    @GetMapping
    public ResponseEntity<List<Photo>> index(@RequestParam(required = false) String name) {
        List<Photo> photos = null;

        if(name == null)
            photos = photoService.findAll().stream().filter( photo -> photo.getVisible()).toList();
        else
            photos = photoService.findByNameContaining(name).stream().filter( photo -> photo.getVisible()).toList();

        return new ResponseEntity<List<Photo>>(photos, HttpStatus.OK);
    }
}
