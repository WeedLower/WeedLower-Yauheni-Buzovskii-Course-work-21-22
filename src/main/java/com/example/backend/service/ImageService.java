package com.example.backend.service;

import com.example.backend.entity.ImageEntity;
import com.example.backend.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void save(ImageEntity image) {
        imageRepository.save(image);
    }

    public Optional<ImageEntity> getOne(int id) {
        return imageRepository.findById(id);
    }

    public boolean exist(int id) {
        return imageRepository.existsById(id);
    }

    public void delete(ImageEntity image) {
        imageRepository.delete(image);
    }
}
