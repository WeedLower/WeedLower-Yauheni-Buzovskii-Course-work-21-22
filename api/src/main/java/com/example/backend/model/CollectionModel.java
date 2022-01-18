package com.example.backend.model;

import com.example.backend.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CollectionModel {
    private int id;
    private String name;
    private UserEntity owner;
    private String description;
    private String topic;

}
