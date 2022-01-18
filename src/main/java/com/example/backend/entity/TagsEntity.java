package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tags",schema = "postgres_db")
public class TagsEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Integer id;

    private String tag;

    public TagsEntity(String tag) {
        this.tag = tag;
    }

    //    @ManyToMany(mappedBy = "tags",fetch = FetchType.EAGER)
    @JsonIgnore
    @OneToMany(mappedBy = "tags")
    private Set<ItemsTag> items;
}
