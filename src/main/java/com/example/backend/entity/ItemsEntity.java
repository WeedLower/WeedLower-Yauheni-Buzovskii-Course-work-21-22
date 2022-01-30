package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.util.*;

@Indexed
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items", schema = "postgres_db")
public class ItemsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Integer id;

    @Column(nullable=false)
    @Field
    private String name;

    private Long optionalNumberField1;
    private Long optionalNumberField2;
    private Long optionalNumberField3;
    @Field
    private String optionalStringField1;
    @Field
    private String optionalStringField2;
    @Field
    private String optionalStringField3;
    @Field
    private String optionalTextField1;
    @Field
    private String optionalTextField2;
    @Field
    private String optionalTextField3;
    private Date optionalDataField1;
    private Date optionalDataField2;
    private Date optionalDataField3;
    private Boolean optionalCheckboxField1;
    private Boolean optionalCheckboxField2;
    private Boolean optionalCheckboxField3;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "items_likes",
            joinColumns = { @JoinColumn(name = "item_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id")})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<UserEntity> likes = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "items",cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<ItemsTag> tags;

    @JsonIgnore
    @OneToMany(mappedBy = "itemsEntity",cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @IndexedEmbedded(depth = 1)
    private List<CommentsEntity> comments = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id",nullable=false)
    private UserEntity user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "collection",referencedColumnName = "id",nullable=false)
    @IndexedEmbedded
    private CollectionsEntity collection;

}
