package com.org.springilmiofotoalbum.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.springilmiofotoalbum.pojos.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    public List<Photo> findByUserId(long userId);

    public List<Photo> findByNameContaining(String name);
}
