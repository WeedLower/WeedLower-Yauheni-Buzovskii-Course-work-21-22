package com.example.backend.service;

import com.example.backend.entity.ItemsEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SearchServiceItr {

    @Transactional
    List<ItemsEntity> searchItems(String searchitm) throws InterruptedException;
}
