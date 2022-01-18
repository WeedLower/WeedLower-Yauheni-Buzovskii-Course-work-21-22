package com.example.backend.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "topic")
    private String topic;

    @ManyToOne
    @JoinColumn(name = "owner",referencedColumnName = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity user;

    @OneToMany(mappedBy = "collection",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<ItemsEntity> items;


}
