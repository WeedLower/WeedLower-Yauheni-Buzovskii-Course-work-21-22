package com.example.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "items_tags", schema = "postgres_db")
public class ItemsTag {
    @EmbeddedId
    private ItemsTagId id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    ItemsEntity items;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @MapsId("tagId")
    @JoinColumn(name = "tag_id")
    TagsEntity tags;

    public ItemsTagId getId() {
        return id;
    }

    public void setId(ItemsTagId id) {
        this.id = id;
    }
}