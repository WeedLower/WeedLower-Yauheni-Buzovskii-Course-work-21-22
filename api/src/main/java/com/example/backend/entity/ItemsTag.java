package com.example.backend.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne
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