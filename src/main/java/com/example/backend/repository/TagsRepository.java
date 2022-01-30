package com.example.backend.repository;

import com.example.backend.entity.TagsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Set;

public interface TagsRepository extends JpaRepository<TagsEntity,Integer> {

    TagsEntity findByTag(String name);

    @Modifying
    @Transactional
    @Query(value = "insert into postgres_db.items_tags(item_id, tag_id) VALUES (:itemId,:tagId)", nativeQuery = true)
    void itemTags(@Param("itemId") Integer itemId, @Param("tagId") Integer tagId);

    @Query(value = "select t from TagsEntity t left join t.items i where i.items.id= :id")
    Set<TagsEntity> findAllByItemId(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "delete from postgres_db.items_tags where item_id=:id", nativeQuery=true)
    void deleteAllByItem(@Param("id")int id);
}
