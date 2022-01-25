package com.example.backend.model;

import com.example.backend.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemModel {
    private int id;
    private String name;
    private CollectionsEntity collection;
    private UserEntity author;
    private Long likes;
    private String[] tags;
    private Set<TagsEntity> tagSet;
    private Boolean meLiked;
    private Long optionalNumberField1;
    private Long optionalNumberField2;
    private Long optionalNumberField3;
    private String optionalStringField1;
    private String optionalStringField2;
    private String optionalStringField3;
    private String optionalTextField1;
    private String optionalTextField2;
    private String optionalTextField3;
    private Date optionalDataField1;
    private Date optionalDataField2;
    private Date optionalDataField3;
    private Boolean optionalCheckboxField1;
    private Boolean optionalCheckboxField2;
    private Boolean optionalCheckboxField3;


    public ItemModel(ItemsEntity item){
        this.id = item.getId();
        this.name = item.getName();
        this.collection=item.getCollection();
        this.author=item.getUser();
        this.optionalNumberField1=item.getOptionalNumberField1();
        this.optionalNumberField2=item.getOptionalNumberField2();
        this.optionalNumberField3=item.getOptionalNumberField3();
        this.optionalStringField1=item.getOptionalStringField1();
        this.optionalStringField2=item.getOptionalStringField2();
        this.optionalStringField3=item.getOptionalStringField3();
        this.optionalTextField1=item.getOptionalTextField1();
        this.optionalTextField2=item.getOptionalTextField2();
        this.optionalTextField3=item.getOptionalTextField3();
        this.optionalDataField1=item.getOptionalDataField1();
        this.optionalDataField2=item.getOptionalDataField2();
        this.optionalDataField3=item.getOptionalDataField3();
        this.optionalCheckboxField1=item.getOptionalCheckboxField1();
        this.optionalCheckboxField2=item.getOptionalCheckboxField2();
        this.optionalCheckboxField3=item.getOptionalCheckboxField3();
    }

    public ItemModel(ItemsEntity item,Long likes){
        this.id = item.getId();
        this.name = item.getName();
        this.collection=item.getCollection();
        this.author=item.getUser();
        this.likes=likes;
        this.optionalNumberField1=item.getOptionalNumberField1();
        this.optionalNumberField2=item.getOptionalNumberField2();
        this.optionalNumberField3=item.getOptionalNumberField3();
        this.optionalStringField1=item.getOptionalStringField1();
        this.optionalStringField2=item.getOptionalStringField2();
        this.optionalStringField3=item.getOptionalStringField3();
        this.optionalTextField1=item.getOptionalTextField1();
        this.optionalTextField2=item.getOptionalTextField2();
        this.optionalTextField3=item.getOptionalTextField3();
        this.optionalDataField1=item.getOptionalDataField1();
        this.optionalDataField2=item.getOptionalDataField2();
        this.optionalDataField3=item.getOptionalDataField3();
        this.optionalCheckboxField1=item.getOptionalCheckboxField1();
        this.optionalCheckboxField2=item.getOptionalCheckboxField2();
        this.optionalCheckboxField3=item.getOptionalCheckboxField3();
    }


    public ItemModel(ItemsEntity item, Long likes, Boolean meLiked) {
        this.id = item.getId();
        this.name = item.getName();
        this.collection=item.getCollection();
        this.author=item.getUser();
        this.likes=likes;
        this.meLiked=meLiked;
        this.optionalNumberField1=item.getOptionalNumberField1();
        this.optionalNumberField2=item.getOptionalNumberField2();
        this.optionalNumberField3=item.getOptionalNumberField3();
        this.optionalStringField1=item.getOptionalStringField1();
        this.optionalStringField2=item.getOptionalStringField2();
        this.optionalStringField3=item.getOptionalStringField3();
        this.optionalTextField1=item.getOptionalTextField1();
        this.optionalTextField2=item.getOptionalTextField2();
        this.optionalTextField3=item.getOptionalTextField3();
        this.optionalDataField1=item.getOptionalDataField1();
        this.optionalDataField2=item.getOptionalDataField2();
        this.optionalDataField3=item.getOptionalDataField3();
        this.optionalCheckboxField1=item.getOptionalCheckboxField1();
        this.optionalCheckboxField2=item.getOptionalCheckboxField2();
        this.optionalCheckboxField3=item.getOptionalCheckboxField3();
    }


}
