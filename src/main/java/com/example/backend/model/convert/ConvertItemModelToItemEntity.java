package com.example.backend.model.convert;

import com.example.backend.entity.ItemsEntity;
import com.example.backend.model.ItemModel;
import org.springframework.core.convert.converter.Converter;

public class ConvertItemModelToItemEntity implements Converter<ItemModel, ItemsEntity> {
    @Override
    public ItemsEntity convert(ItemModel source) {
        ItemsEntity item = new ItemsEntity();
        item.setName(source.getName());
        item.setCollection(source.getCollection());
        item.setUser(source.getAuthor());
        item.setOptionalNumberField1(source.getOptionalNumberField1());
        item.setOptionalNumberField2(source.getOptionalNumberField2());
        item.setOptionalNumberField3(source.getOptionalNumberField3());
        item.setOptionalStringField1(source.getOptionalStringField1());
        item.setOptionalStringField2(source.getOptionalStringField2());
        item.setOptionalStringField3(source.getOptionalStringField3());
        item.setOptionalTextField1(source.getOptionalTextField1());
        item.setOptionalTextField2(source.getOptionalTextField2());
        item.setOptionalTextField3(source.getOptionalTextField3());
        item.setOptionalDataField1(source.getOptionalDataField1());
        item.setOptionalDataField2(source.getOptionalDataField2());
        item.setOptionalDataField3(source.getOptionalDataField3());
        item.setOptionalCheckboxField1(source.getOptionalCheckboxField1());
        item.setOptionalCheckboxField2(source.getOptionalCheckboxField2());
        item.setOptionalCheckboxField3(source.getOptionalCheckboxField3());
        return item;

    }
}
