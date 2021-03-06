package com.example.backend.repository;

import com.example.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface UsersRepository extends JpaRepository<UserEntity,Integer> {

    UserEntity findByEmail(String email);
    UserEntity findByName(String name);

    @Modifying
    @Transactional
    @Query("update UserEntity i set i.role='ADMIN',i.changed=true  where i.id= :id")
    void setRoleAdmin(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("update UserEntity i set i.role='USER',i.changed=true where i.id= :id")
    void setRoleUser(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("update UserEntity i set i.status=true where i.id= :id")
    void blockUser(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("update UserEntity i set i.status=false where i.id=:id")
    void unblockUser(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "delete from UserEntity i where i.id = :id")
    void deleteByUserId(@Param("id")Integer id);

    @Query(value = "select i.status from UserEntity i where i.id=:id")
    boolean checkStatus(@Param("id")Integer id);

    @Query(value="select i.changed from UserEntity i where i.id = :id")
    boolean checkChanged(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "update UserEntity i set i.changed=false where i.id = :id")
    void updateChanged(@Param("id")Integer id);
}
