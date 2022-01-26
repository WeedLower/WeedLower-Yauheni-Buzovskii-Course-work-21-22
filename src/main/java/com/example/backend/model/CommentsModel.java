package com.example.backend.model;

import com.example.backend.entity.ItemsEntity;
import com.example.backend.entity.UserEntity;
import lombok.*;

@Builder
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
