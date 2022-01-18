package com.example.backend.repository;

import com.example.backend.entity.ItemsEntity;
import com.example.backend.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ItemsRepository extends JpaRepository<ItemsEntity,Integer> {

    ItemsEntity findByName(String name);

    @Query(value = "select new com.example.backend.model.ItemModel(i) from ItemsEntity i where i.collection.id=:id")
    List<ItemModel> findAllByCollection_Id(@Param("id") Integer id);

    @Query(value = "select new com.example.backend.model.ItemModel(i,count (a.id.userId)) from ItemsEntity i left join ItemsLikes a on i.id=a.id.itemId where i.id= :id group by i")
    Optional<ItemModel> findItemById(@Param("id") Integer id);

    @Query(value = "select new com.example.backend.model.ItemModel(i,count (a.id.userId),sum (case when a.id.userId = :userId then 1 else 0 end ) > 0) from ItemsEntity i left join ItemsLikes a on i.id=a.id.itemId where i.id = :id group by i")
    Optional<ItemModel> findItemAuthUser(@Param("id") Integer id,@Param("userId") Integer userId);

    @Transactional
    @Modifying
    @Query(value = "insert into postgres_db.items_likes(item_id, user_id) VALUES (:itemId,:userId)",nativeQuery = true)
    void like(@Param("itemId") Integer id,@Param("userId") Integer id1);

    @Query(value = "select i from ItemsEntity i left join ItemsTag t on i.id=t.id.itemId where t.id.tagId= :id group by i")
    List<ItemsEntity> findItemsByTagsId(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "delete from ItemsEntity i where i.id= :id")
    void deleteById(@Param("id") Integer id);
}