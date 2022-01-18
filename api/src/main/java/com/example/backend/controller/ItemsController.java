package com.example.backend.controller;

import com.example.backend.entity.ItemsEntity;
import com.example.backend.entity.UserEntity;
import com.example.backend.model.ItemModel;
import com.example.backend.model.UserModel;
import com.example.backend.service.ItemsService;
import com.example.backend.service.UsersServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/items")
public class ItemsController {

    private ItemsService itemsService;

    @Autowired
    public ItemsController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<ItemsEntity> findAll(){
        return itemsService.getAllItems();
    }



    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ItemsEntity saveItem(@RequestBody ItemModel item){
        return itemsService.saveItem(item);
    }

    @RequestMapping(value = "/name/{name}",method = RequestMethod.GET)
    public ResponseEntity<ItemsEntity> findItemByName(@PathVariable(name = "name")String name){
        ItemsEntity item = itemsService.getItemByName(name);
        return ResponseEntity.ok(item);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<ItemModel> findItemById(@PathVariable(name = "id")Integer id, Principal user){
        if(user==null){
            Optional<ItemModel> items = itemsService.findItemById(id);
            if (items.isPresent()){
                return ResponseEntity.ok(items.get());
            }else {
                return ResponseEntity.notFound().build();
            }
        }else{
            Optional<ItemModel> items= itemsService.findItemAuthUser(id,user);
            if (items.isPresent()){
                return ResponseEntity.ok(items.get());
            }else {
                return ResponseEntity.notFound().build();
            }
        }
    }

    @RequestMapping(value = "/tag/{id}",method = RequestMethod.GET)
    public List<ItemsEntity> findItemsByTagsId(@PathVariable(name = "id")Integer id){
        return itemsService.findItemsByTagId(id);
    }

    @RequestMapping(value = "/collect/{id}",method = RequestMethod.GET)
    public List<ItemModel> findItemByCollectionId(@PathVariable(name = "id")Integer id){
        return itemsService.getAllByCollectionId(id);
    }

    @RequestMapping(value = "/like",method = RequestMethod.POST)
    public void likeItem(@RequestBody Integer id,Principal user){
        itemsService.like(id,user);
    }

    @RequestMapping(value ="/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") Integer id){
        itemsService.delete(id);
    }
}
