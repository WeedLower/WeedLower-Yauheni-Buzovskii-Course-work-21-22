package com.example.backend.repository;

import com.example.backend.entity.CollectionsEntity;
import com.example.backend.entity.ItemsEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;


public interface CollectionRepository extends JpaRepository<CollectionsEntity,Integer>, CrudRepository<CollectionsEntity, Integer> {

    CollectionsEntity findByName(String name);

    List<CollectionsEntity> findByUser_Id(Integer id);


    @Query(value = "select c.*,count(a.user_id) from postgres_db.collections c left join postgres_db.items a on c.id=a.collection group by c.id order by count(a.user_id) DESC LIMIT 5",nativeQuery = true)
    List<CollectionsEntity> sortByItems();

    @Modifying
    @Transactional
    @Query(value="delete from CollectionsEntity i where i.id= :id")
    void deleteById(@Param("id")Integer id);
}
