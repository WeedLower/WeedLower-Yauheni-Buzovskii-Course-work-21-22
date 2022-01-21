package com.example.backend.service;

import com.example.backend.entity.ItemsEntity;
import com.example.backend.entity.TagsEntity;
import com.example.backend.entity.UserEntity;
import com.example.backend.model.ItemModel;
import com.example.backend.model.TagsModel;
import com.example.backend.model.convert.ConvertItemModelToItemEntity;
import com.example.backend.repository.ItemsRepository;
import com.example.backend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
        ConvertItemModelToItemEntity convert = new ConvertItemModelToItemEntity();
        ItemsEntity item = convert.convert(items);
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
        itemModelList.forEach(s-> {
            List<String> list = new ArrayList<>();
            for (TagsEntity tagsEntity : s.getTagSet()) {
                String toString = tagsEntity.getTag().toString();
                list.add(toString);
            }
            s.setTags(list.toArray(new String[0]));
        });
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
        Optional<ItemModel> item = itemsRepository.findItemAuthUser(id,userEntity.getId());
        if (item.isPresent()){
            item.get().setTagSet(tagsService.findTagsByItemId(item.get().getId()));
            List<String> tags = new ArrayList<>();
            for(TagsEntity tagsEntity : item.get().getTagSet()){
                String toString = tagsEntity.getTag().toString();
                tags.add(toString);
            }
            item.get().setTags(tags.toArray(new String[0]));
        }
        return item;
    }

    public void like(Integer id, Principal user) {
        UserEntity userEntity = usersRepository.findByEmail(user.getName());
        itemsRepository.like(id,userEntity.getId());
    }

    public List<ItemsEntity> findItemsByTagId(Integer id) {
      return   itemsRepository.findItemsByTagsId(id);
    }

    public void updateItem(ItemModel item) throws Exception {
        tagsService.checkTags(item);
        itemsRepository.update(item.getId(),item.getName(),item.getOptionalNumberField1(),
                item.getOptionalNumberField2(),item.getOptionalNumberField3(),
                item.getOptionalStringField1(),item.getOptionalStringField2(),
                item.getOptionalStringField3(), item.getOptionalTextField1(),
                item.getOptionalTextField2(),item.getOptionalTextField3(),
                item.getOptionalDataField1(),item.getOptionalDataField2(),
                item.getOptionalDataField3(),item.getOptionalCheckboxField1(),
                item.getOptionalCheckboxField2(),item.getOptionalCheckboxField3());

    }
}
