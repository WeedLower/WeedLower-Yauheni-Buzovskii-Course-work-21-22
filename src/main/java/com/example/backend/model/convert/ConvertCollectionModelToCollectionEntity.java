package com.example.backend.model.convert;

import com.example.backend.entity.CollectionsEntity;
import com.example.backend.model.CollectionModel;
import org.springframework.core.convert.converter.Converter;

public class ConvertCollectionModelToCollectionEntity implements Converter<CollectionModel, CollectionsEntity> {
    @Override
    public CollectionsEntity convert(CollectionModel source) {
        CollectionsEntity collection = CollectionsEntity.builder()
                .name(source.getName()).user(source.getOwner()).description(source.getDescription()).topic(source.getTopic())
                .numberName1(source.getNumberName1()).numberName2(source.getNumberName2()).numberName3(source.getNumberName3())
                .stringName1(source.getStringName1()).stringName2(source.getStringName2()).stringName3(source.getStringName3())
                .textName1(source.getTextName1()).textName2(source.getTextName2()).textName3(source.getTextName3())
                .dataName1(source.getDataName1()).dataName2(source.getDataName2()).dataName3(source.getDataName3())
                .checkboxName1(source.getCheckboxName1()).checkboxName2(source.getCheckboxName2()).checkboxName3(source.getCheckboxName3())
                .image(source.getImg()).build();
        return collection;
    }
}
