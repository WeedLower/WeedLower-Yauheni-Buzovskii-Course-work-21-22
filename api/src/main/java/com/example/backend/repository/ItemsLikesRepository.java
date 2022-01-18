package com.example.backend.repository;

import com.example.backend.entity.ItemsLikes;
import com.example.backend.entity.ItemsLikesId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsLikesRepository extends JpaRepository<ItemsLikes, ItemsLikesId> {

}