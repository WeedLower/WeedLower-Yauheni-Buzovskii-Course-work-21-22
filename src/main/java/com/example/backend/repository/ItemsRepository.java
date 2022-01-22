package com.example.backend.repository;

import com.example.backend.entity.ItemsEntity;
import com.example.backend.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ItemsRepository extends JpaRepository<ItemsEntity,Integer> {

    ItemsEntity findByName(String name);

    @Query(value = "select new com.example.backend.model.ItemModel(i) from ItemsEntity i where i.collection.id=:id")
    List<ItemModel> findAllByCollection_Id(@Param("id") Integer id);

    @Query(value = "select new com.example.backend.model.ItemModel(i,count (a.id.userId)) from ItemsEntity i left join ItemsLikes a on i.id=a.id.itemId where i.id= :id group by i")
    Optional<ItemModel> findItemById(@Param("id") Integer id);

    @Query(value = "select new com.example.backend.model.ItemModel(i,count (a.id.userId),sum (case when a.id.userId = :userId then 1 else 0 end ) > 0) from ItemsEntity i left join ItemsLikes a on i.id=a.id.itemId where i.id = :id group by i")
    Optional<ItemModel> findItemAuthUser(@Param("id") Integer id, @Param("userId") Integer userId);

    @Transactional
    @Modifying
    @Query(value = "insert into postgres_db.items_likes(item_id, user_id) VALUES (:itemId,:userId)", nativeQuery = true)
    void like(@Param("itemId") Integer id, @Param("userId") Integer id1);

    @Query(value = "select i from ItemsEntity i left join ItemsTag t on i.id=t.id.itemId where t.id.tagId= :id group by i")
    List<ItemsEntity> findItemsByTagsId(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("update ItemsEntity i set i.name = :name , i.optionalNumberField1 = :number1,i.optionalNumberField2 = :number2"+
            ",i.optionalNumberField3=:number3,i.optionalStringField1=:string1,i.optionalStringField2=:string2"+
            ",i.optionalStringField3=:string3,i.optionalTextField1=:text1,i.optionalTextField2=:text2"+
            ",i.optionalTextField3=:text3,i.optionalDataField1=:date1,i.optionalDataField2=:date2,i.optionalDataField3=:date3"+
            ",i.optionalCheckboxField1=:bol1,i.optionalCheckboxField2=:bol2,i.optionalCheckboxField3=:bol3 where i.id =:id")
    void update(@Param("id") Integer id, @Param("name") String name, @Param("number1") Long number1,
                @Param("number2") Long number2,@Param("number3") Long number3,@Param("string1")String string1,
                @Param("string2")String string2,@Param("string3")String string3,@Param("text1")String text1,
                @Param("text2")String text2, @Param("text3")String text3,@Param("date1")Date date1,
                @Param("date2")Date date2, @Param("date3") Date date3, @Param("bol1")Boolean bol1,
                @Param("bol2")Boolean bol2, @Param("bol3")Boolean bol3);

    @Query(value="select i.* from postgres_db.items i  order by i.item_id desc limit 5",nativeQuery=true)
    List<ItemsEntity> findLastAdd();
}
