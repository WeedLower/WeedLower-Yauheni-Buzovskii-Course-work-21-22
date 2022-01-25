package com.example.backend.repository;

import com.example.backend.entity.ItemsTag;
import com.example.backend.entity.ItemsTagId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsTagRepository extends JpaRepository<ItemsTag, ItemsTagId> {

}