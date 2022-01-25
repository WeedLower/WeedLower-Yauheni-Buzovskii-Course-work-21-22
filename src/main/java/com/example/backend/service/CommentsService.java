package com.example.backend.service;

import com.example.backend.entity.CommentsEntity;
import com.example.backend.model.CommentsModel;
import com.example.backend.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsService {

    private final CommentsRepository commentsRepository;

    @Autowired
    public CommentsService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    public List<CommentsEntity> getAllByItemId(Integer id) {
       return this.commentsRepository.findAllByItemsEntity_Id(id);
    }

    public CommentsEntity save(CommentsModel comments) {
        CommentsEntity commentsEntity = new CommentsEntity();
        commentsEntity.setComment(comments.getComment());
        commentsEntity.setItemsEntity(comments.getItem());
        commentsEntity.setUser(comments.getOwner());
        return this.commentsRepository.save(commentsEntity);
    }
}
