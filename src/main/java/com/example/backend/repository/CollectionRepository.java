package com.example.backend.repository;

import com.example.backend.entity.CollectionsEntity;
import com.example.backend.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;


public interface CollectionRepository extends JpaRepository<CollectionsEntity,Integer>{

    CollectionsEntity findByName(String name);

    List<CollectionsEntity> findByUser_Id(Integer id);


    @Query(value = "select c.*,count(a.user_id) from postgres_db.collections c left join postgres_db.items a " +
            "on c.id=a.collection group by c.id order by count(a.user_id) DESC LIMIT 5",nativeQuery = true)
    List<CollectionsEntity> sortByItems();

    @Modifying
    @Transactional
    @Query(value="delete from CollectionsEntity i where i.id= :id")
    void deleteById(@Param("id")Integer id);

    @Modifying
    @Transactional
    @Query(value = "update CollectionsEntity i set " +
            "i.name = :name,i.description=:desc,i.topic=:topic,i.image=:img,"+
            "i.numberName1=:n1,i.numberName2=:n2,i.numberName3=:n3," +
            "i.stringName1=:s1,i.stringName2=:s2,i.stringName3=:s3," +
            "i.textName1=:t1,i.textName2=:t2,i.textName3=:t3," +
            "i.dataName1=:d1,i.dataName2=:d2,i.dataName3=:d3," +
            "i.checkboxName1=:ch1,i.checkboxName2=:ch2,i.checkboxName3=:ch3 where i.id = :id")
    void edit(@Param("id")Integer id, @Param("name")String name, @Param("desc")String description, @Param("topic")String topic,
              @Param("img") ImageEntity image, @Param("n1")String n1,@Param("n2")String n2,@Param("n3")String n3,@Param("s1")String s1,
              @Param("s2")String s2,@Param("s3")String s3,@Param("t1")String t1,@Param("t2")String t2,@Param("t3")String t3,
              @Param("d1")String d1, @Param("d2")String d2,@Param("d3")String d3,@Param("ch1")String ch1,@Param("ch2")String ch2,
              @Param("ch3")String ch3);
}
