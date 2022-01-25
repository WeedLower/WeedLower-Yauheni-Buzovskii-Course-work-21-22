package com.example.backend.controller;

import com.example.backend.entity.TagsEntity;
import com.example.backend.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/tags")
public class TagsController {

    private final TagsService tagsService;

    @Autowired
    public TagsController(TagsService tagsService) {
        this.tagsService = tagsService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<TagsEntity> getAll(){
        return tagsService.getAll();
    }

}
