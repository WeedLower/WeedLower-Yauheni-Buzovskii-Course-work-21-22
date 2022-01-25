package com.example.backend.controller;


import com.example.backend.entity.ImageEntity;
import com.example.backend.model.MessageResponse;
import com.example.backend.service.CloudinaryService;
import com.example.backend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/cloudinary")
public class CloudinaryController {

    private final CloudinaryService cloudinary;
    private final ImageService imageService;


    @Autowired
    public CloudinaryController(CloudinaryService cloudinary, ImageService imageService) {
        this.cloudinary = cloudinary;
        this.imageService = imageService;
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public ResponseEntity<ImageEntity> getImg(@PathVariable(name = "id")int id){
        Optional<ImageEntity> image = imageService.getOne(id);
        return image.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity(new MessageResponse("get faild"), HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value= "/upload",method = RequestMethod.POST)
    public ResponseEntity<ImageEntity> upload (@RequestParam MultipartFile multipartFile) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if (bi == null) {
            return new ResponseEntity(new MessageResponse("No valid img"),HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinary.upload(multipartFile);
        ImageEntity image = new ImageEntity((String)result.get("original_filename"),
                (String)result.get("url"),
                (String)result.get("public_id"));
        imageService.save(image);
        return  ResponseEntity.ok(image);
    }

    @RequestMapping(value= "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> delete (@PathVariable(name = "id") int id) throws IOException {
        if(!imageService.exist(id))
            return new ResponseEntity(new MessageResponse("not found"),HttpStatus.NOT_FOUND);
        ImageEntity image = imageService.getOne(id).get();
        Map result = cloudinary.delete(image.getImageId());
        imageService.delete(image);
        return new ResponseEntity(new MessageResponse("image delete"),HttpStatus.OK);
    }
}
