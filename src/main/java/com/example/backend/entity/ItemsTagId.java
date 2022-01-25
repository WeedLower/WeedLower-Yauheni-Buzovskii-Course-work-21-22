package com.example.backend.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItemsTagId implements Serializable {
    private static final long serialVersionUID = 6485091650566344796L;

    @Column(name = "item_id", nullable = false)
    private Integer itemId;
    @Column(name = "tag_id", nullable = false)
    private Integer tagId;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, tagId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ItemsTagId entity = (ItemsTagId) o;
        return Objects.equals(this.itemId, entity.itemId) &&
                Objects.equals(this.tagId, entity.tagId);
    }
}