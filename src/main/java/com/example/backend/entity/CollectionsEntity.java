package com.example.backend.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.search.annotations.Field;

import javax.persistence.*;
import java.util.List;



@Getter
@Setter
@Entity
@Table(name = "collections",schema = "postgres_db")
public class CollectionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @Field
    private String name;

    @Column(name = "description")
    @Field
    private String description;

    @Column(name = "topic")
    private String topic;

    private String numberName1;
    private String numberName2;
    private String numberName3;
    private String stringName1;
    private String stringName2;
    private String stringName3;
    private String textName1;
    private String textName2;
    private String textName3;
    private String dataName1;
    private String dataName2;
    private String dataName3;
    private String checkboxName1;
    private String checkboxName2;
    private String checkboxName3;

    @ManyToOne
    @JoinColumn(name = "owner",referencedColumnName = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity user;

    @OneToMany(mappedBy = "collection",fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<ItemsEntity> items;

    @OneToOne
    private ImageEntity image;




}
