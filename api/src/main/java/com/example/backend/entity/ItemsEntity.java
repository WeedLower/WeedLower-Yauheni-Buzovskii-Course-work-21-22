package com.example.backend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
    private String name;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "items_likes",
            joinColumns = { @JoinColumn(name = "item_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id")})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<UserEntity> likes = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "items")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<ItemsTag> tags;

    @JsonIgnore
    @OneToMany(mappedBy = "itemsEntity",cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<CommentsEntity> comments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id",nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "collection",referencedColumnName = "id",nullable=false)
    private CollectionsEntity collection;

}
