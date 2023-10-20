package com.org.springilmiofotoalbum.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.springilmiofotoalbum.pojos.Photo;
import com.org.springilmiofotoalbum.repositories.PhotoRepository;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public void save(Photo photo) {
        photoRepository.save(photo);
    }

    public void saveAll(List<Photo> photos) {
        photoRepository.saveAll(photos);
    }

    public Photo findById(long id) {
        Photo photo = null;
        Optional<Photo> oPhoto = photoRepository.findById(id);

        if(oPhoto.isPresent())
            photo = oPhoto.get();

        return photo;
    }

    public List<Photo> findByNameContaining(String name) {
        return photoRepository.findByNameContaining(name);
    } 

    public List<Photo> findByUserId(long userId) {
        return photoRepository.findByUserId(userId);
    }

    public List<Photo> findAll() {
        return photoRepository.findAll();
    }

    public void deleteById(long id) {
        photoRepository.deleteById(id);
    }
}
