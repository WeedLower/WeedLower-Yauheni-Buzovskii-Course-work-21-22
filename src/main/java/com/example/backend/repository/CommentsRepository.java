package com.example.backend.repository;

import com.example.backend.entity.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<CommentsEntity,Integer> {

    List<CommentsEntity> findAllByItemsEntity_Id(Integer id);
}
