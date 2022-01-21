package com.example.backend.model;

import com.example.backend.entity.ImageEntity;
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
    private ImageEntity img;
    private String numberName1;
    private String numberName2;
    private String numberName3;
    private String stringName1;
    private String stringName2;
    private String stringName3;
    private String textName1;
    private String textName2;
    private String textName3;
    private String dataName1;
    private String dataName2;
    private String dataName3;
    private String checkboxName1;
    private String checkboxName2;
    private String checkboxName3;

}
