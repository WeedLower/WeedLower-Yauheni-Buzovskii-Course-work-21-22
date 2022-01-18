package com.example.backend.service;

import com.example.backend.entity.CollectionsEntity;
import com.example.backend.model.CollectionModel;
import com.example.backend.repository.CollectionRepository;
import com.example.backend.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CollectionsService {

    private CollectionRepository collectionRepository;


    @Autowired
    public CollectionsService(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    public List<CollectionsEntity> getAllCollections(){
        return collectionRepository.sortByItems();
    }

    public CollectionsEntity saveCollection(CollectionModel collection){
        CollectionsEntity collections= new CollectionsEntity();
        collections.setName(collection.getName());
        collections.setUser(collection.getOwner());
        collections.setDescription(collection.getDescription());
        collections.setTopic(collection.getTopic());

        return collectionRepository.save(collections);
    }

    public CollectionsEntity getCollectionsByName(String name){
        return collectionRepository.findByName(name);
    }

    public List<CollectionsEntity> getCollectionsByUserId(Integer id){
        return collectionRepository.findByUser_Id(id);
    }

    public Optional<CollectionsEntity> getCollectionById(Integer id){
        return collectionRepository.findById(id);
    }

    public void delete(Integer id) {
        collectionRepository.deleteById(id);
    }

}
