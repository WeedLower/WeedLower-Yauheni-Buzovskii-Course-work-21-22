package com.example.backend.service;

import com.example.backend.entity.CollectionsEntity;
import com.example.backend.model.CollectionModel;
import com.example.backend.model.convert.ConvertCollectionModelToCollectionEntity;
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
        ConvertCollectionModelToCollectionEntity convert = new ConvertCollectionModelToCollectionEntity();
        CollectionsEntity collections = convert.convert(collection);
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

    public void edit(CollectionModel collection) {

        collectionRepository.edit(collection.getId(),collection.getName(),collection.getDescription(),collection.getTopic(),
                collection.getImg(),collection.getNumberName1(),collection.getNumberName2(),collection.getNumberName3(),
                collection.getStringName1(),collection.getStringName2(),collection.getStringName3(),
                collection.getTextName1(),collection.getTextName2(),collection.getTextName3(),collection.getDataName1(),
                collection.getDataName2(),collection.getDataName3(),collection.getCheckboxName1(),
                collection.getCheckboxName2(),collection.getCheckboxName3());
    }
}