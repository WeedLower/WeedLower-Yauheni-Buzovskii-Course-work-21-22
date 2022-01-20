package com.example.backend.controller;

import com.example.backend.entity.CollectionsEntity;
import com.example.backend.entity.UserEntity;
import com.example.backend.model.CollectionModel;
import com.example.backend.service.CollectionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/collections")
public class CollectionController {

    private CollectionsService collectionsService;

    @Autowired
    public CollectionController(CollectionsService collectionsService) {
        this.collectionsService = collectionsService;
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public List<CollectionsEntity> getAllCollections(){
        return collectionsService.getAllCollections();
    }

    @RequestMapping(method = RequestMethod.POST)
    public CollectionsEntity saveCollection(@RequestBody CollectionModel collection){
        return collectionsService.saveCollection(collection);
    }

    @RequestMapping(value = "/name/{name}",method = RequestMethod.GET)
    public ResponseEntity<CollectionsEntity> findCollectionByName(@PathVariable(name = "name")String name){
        CollectionsEntity collection= collectionsService.getCollectionsByName(name);
        return ResponseEntity.ok(collection);
    }

    @RequestMapping(value = "/user/{user}", method = RequestMethod.GET)
    public List<CollectionsEntity> findCollectionsByUserId(@PathVariable(name = "user")Integer id){
        return collectionsService.getCollectionsByUserId(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<CollectionsEntity> findCollectionById(@PathVariable(name = "id")Integer id){
        Optional<CollectionsEntity> collection = collectionsService.getCollectionById(id);
        if(collection.isPresent()){
            return ResponseEntity.ok(collection.get());
        }else {

            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public void deleteById(@PathVariable(name = "id")Integer id){
        collectionsService.delete(id);
    }

}
