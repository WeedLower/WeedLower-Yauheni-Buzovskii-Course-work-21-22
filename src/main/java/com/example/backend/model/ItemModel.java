package com.example.backend.model;

import com.example.backend.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemModel {
    private int id;
    private String name;
    private CollectionsEntity collection;
    private UserEntity author;
    private Long likes;
    private String[] tags;
    private Set<TagsEntity> tagSet;
    private Boolean meLiked;


    public ItemModel(ItemsEntity item){
        this.id = item.getId();
        this.name = item.getName();
        this.collection=item.getCollection();
        this.author=item.getUser();
    }

    public ItemModel(ItemsEntity item,Long likes){
        this.id = item.getId();
        this.name = item.getName();
        this.collection=item.getCollection();
        this.author=item.getUser();
        this.likes=likes;
    }


    public ItemModel(ItemsEntity item, Long likes, Boolean meLiked) {
        this.id = item.getId();
        this.name = item.getName();
        this.collection=item.getCollection();
        this.author=item.getUser();
        this.likes=likes;
        this.meLiked=meLiked;
    }


}
