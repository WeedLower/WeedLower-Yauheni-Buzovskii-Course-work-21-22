package com.example.backend.service;

import com.example.backend.entity.ItemsEntity;
import com.example.backend.entity.TagsEntity;
import com.example.backend.entity.UserEntity;
import com.example.backend.model.ItemModel;
import com.example.backend.model.TagsModel;
import com.example.backend.repository.ItemsRepository;
import com.example.backend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;

@Service
public class ItemsService {

    private ItemsRepository itemsRepository;
    private UsersRepository usersRepository;
    private TagsService tagsService;

    @Autowired
    public ItemsService(ItemsRepository itemsRepository, UsersRepository usersRepository, TagsService tagsService) {
        this.itemsRepository = itemsRepository;
        this.usersRepository = usersRepository;
        this.tagsService = tagsService;
    }

    public List<ItemsEntity> getAllItems(){
       return itemsRepository.findAll();
    }

    public ItemsEntity saveItem(ItemModel items){
        Set<TagsEntity> tags= tagsService.findTagsByMame(items.getTags());
        ItemsEntity item = new ItemsEntity();
        item.setName(items.getName());
        item.setCollection(items.getCollection());
        item.setUser(items.getAuthor());
        ItemsEntity itemsEntity = itemsRepository.save(item);
        tags.forEach(s -> tagsService.saveItemTags(item.getId(),s.getId()));
        return itemsEntity;
    }

    public ItemsEntity getItemByName(String name){
        return itemsRepository.findByName(name);
    }

    public List<ItemModel> getAllByCollectionId(Integer id){
        List<ItemModel> itemModelList = itemsRepository.findAllByCollection_Id(id);
        itemModelList.forEach(s->  s.setTagSet(tagsService.findTagsByItemId(s.getId())));
        return itemModelList;
    }

    public Optional<ItemModel> findItemById(Integer id){
        return itemsRepository.findItemById(id);
    }


    public void delete(Integer id) {
        itemsRepository.deleteById(id);
    }


    public Optional<ItemModel> findItemAuthUser(Integer id, Principal user) {
        UserEntity userEntity = usersRepository.findByEmail(user.getName());
        return itemsRepository.findItemAuthUser(id,userEntity.getId());
    }

    public void like(Integer id, Principal user) {
        UserEntity userEntity = usersRepository.findByEmail(user.getName());
        itemsRepository.like(id,userEntity.getId());
    }

    public List<ItemsEntity> findItemsByTagId(Integer id) {
      return   itemsRepository.findItemsByTagsId(id);
    }
}
