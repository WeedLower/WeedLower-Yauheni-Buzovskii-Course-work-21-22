package com.example.backend.model;

import com.example.backend.entity.ItemsEntity;
import com.example.backend.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentsModel {
    private int id;
    private UserEntity owner;
    private String comment;
    private ItemsEntity item;
}
