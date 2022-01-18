package com.example.backend.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "items_likes", schema = "postgres_db")
public class ItemsLikes {
    @EmbeddedId
    private ItemsLikesId id;

    public ItemsLikesId getId() {
        return id;
    }

    public void setId(ItemsLikesId id) {
        this.id = id;
    }
}