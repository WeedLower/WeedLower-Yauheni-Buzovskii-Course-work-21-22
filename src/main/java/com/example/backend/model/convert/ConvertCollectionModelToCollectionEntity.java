package com.example.backend.model.convert;

import com.example.backend.entity.CollectionsEntity;
import com.example.backend.model.CollectionModel;
import org.springframework.core.convert.converter.Converter;

public class ConvertCollectionModelToCollectionEntity implements Converter<CollectionModel, CollectionsEntity> {
    @Override
    public CollectionsEntity convert(CollectionModel source) {
        CollectionsEntity collection = new CollectionsEntity();
        collection.setName(source.getName());
        collection.setUser(source.getOwner());
        collection.setDescription(source.getDescription());
        collection.setTopic(source.getTopic());
        collection.setImage(source.getImg());
        collection.setNumberName1(source.getNumberName1());
        collection.setNumberName2(source.getNumberName2());
        collection.setNumberName3(source.getNumberName3());
        collection.setStringName1(source.getStringName1());
        collection.setStringName2(source.getStringName2());
        collection.setStringName3(source.getStringName3());
        collection.setTextName1(source.getTextName1());
        collection.setTextName2(source.getTextName2());
        collection.setTextName3(source.getTextName3());
        collection.setDataName1(source.getDataName1());
        collection.setDataName2(source.getDataName2());
        collection.setDataName3(source.getDataName3());
        collection.setCheckboxName1(source.getCheckboxName1());
        collection.setCheckboxName2(source.getCheckboxName2());
        collection.setCheckboxName3(source.getCheckboxName3());
        return collection;
    }
}
