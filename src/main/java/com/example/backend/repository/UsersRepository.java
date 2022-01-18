package com.example.backend.repository;


import com.example.backend.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;

public interface UsersRepository extends JpaRepository<UserEntity,Integer> {

    UserEntity findByEmail(String email);
    UserEntity findByName(String name);

    @Modifying
    @Transactional
    @Query("update UserEntity i set i.role='ADMIN'  where i.id= :id")
    void setRoleAdmin(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("update UserEntity i set i.role='USER' where i.id= :id")
    void setRoleUser(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("update UserEntity i set i.status=true where i.id= :id")
    void blockUser(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("update UserEntity i set i.status=false where i.id=:id")
    void unblockUser(@Param("id") Integer id);

    void deleteById(Integer id);
}