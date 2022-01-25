package com.example.backend.controller;

import com.example.backend.entity.UserEntity;
import com.example.backend.service.UsersServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersServise usersServise;

    @Autowired
    public UsersController(UsersServise usersServise) {
        this.usersServise = usersServise;
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public List<UserEntity> getAllUsers(){
        return usersServise.getAllUsers();
    }


    @RequestMapping(value = "/email/{email}",method = RequestMethod.GET)
    public ResponseEntity<UserEntity> findUserByEmail(@PathVariable(name = "email") String email){
        UserEntity user = usersServise.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/name/{name}",method = RequestMethod.GET)
    public ResponseEntity<UserEntity> findUserByName(@PathVariable(name = "name") String name){
        UserEntity user = usersServise.findByName(name);
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id")Integer id){
        usersServise.delete(id);
    }

    @RequestMapping(value = "/block/{id}",method = RequestMethod.GET)
    public void blockUser(@PathVariable(name = "id") Integer id){
        usersServise.blockUser(id);
    }

    @RequestMapping(value = "/unblock/{id}",method = RequestMethod.GET)
    public void unBlockUser(@PathVariable(name = "id") Integer id){
        usersServise.unblockUser(id);
    }

    @RequestMapping(value = "/setadmin/{id}",method = RequestMethod.GET)
    public void setRoleAdmin(@PathVariable(name = "id") Integer id){
        usersServise.setRoleAdmin(id);
    }

    @RequestMapping(value = "/setuser/{id}",method = RequestMethod.GET)
    public void setRoleUser(@PathVariable(name = "id") Integer id){
        usersServise.setRoleUser(id);
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<UserEntity> findUserById(@PathVariable(name = "id")Integer id){
        Optional<UserEntity> user = usersServise.findUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value="/check/{id}",method = RequestMethod.GET)
    public boolean check(@PathVariable(name = "id")Integer id){
        return usersServise.check(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UserEntity saveUser(@RequestBody UserEntity user){
        return usersServise.saveUser(user);
    }
}
