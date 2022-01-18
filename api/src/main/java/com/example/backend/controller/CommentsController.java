package com.example.backend.controller;

import com.example.backend.entity.CommentsEntity;
import com.example.backend.model.CommentsModel;
import com.example.backend.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comments")
public class CommentsController {

    private final CommentsService commentsService;

    @Autowired
    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @RequestMapping(value = "/item/{id}",method = RequestMethod.GET)
    public List<CommentsEntity> getAllByItemId(@PathVariable(name = "id")Integer id){
        return commentsService.getAllByItemId(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public CommentsEntity saveComment(@RequestBody CommentsModel comments){
        return commentsService.save(comments);
    }
}
