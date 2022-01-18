package com.example.backend.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItemsLikesId implements Serializable {
    private static final long serialVersionUID = 486703787991333025L;
    @Column(name = "item_id", nullable = false)
    private Integer itemId;
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, userId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ItemsLikesId entity = (ItemsLikesId) o;
        return Objects.equals(this.itemId, entity.itemId) &&
                Objects.equals(this.userId, entity.userId);
    }
}