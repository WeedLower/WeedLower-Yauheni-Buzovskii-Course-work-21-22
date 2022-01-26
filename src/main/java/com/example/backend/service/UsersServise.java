package com.example.backend.service;

import com.example.backend.entity.Role;
import com.example.backend.entity.UserEntity;
import com.example.backend.model.UserModel;
import com.example.backend.model.convert.ConvertUserEntityToUserModel;
import com.example.backend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("customUserDetailsService")
public class UsersServise implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersServise(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<UserModel> getAllUsers(){
        List<UserEntity> getAllUsers = usersRepository.findAll();
        ConvertUserEntityToUserModel convert = new ConvertUserEntityToUserModel();
        List<UserModel> users = new ArrayList<>();
        getAllUsers.forEach(s->users.add(convert.convert(s)));
        return users;
    }

    public UserEntity findUserEntityByEmail(String email){
        return usersRepository.findByEmail(email);
    }

    public UserModel findByEmail(String email){
        UserEntity userEntity = usersRepository.findByEmail(email);
        if (userEntity==null){
            return null;
        }
        UserModel user= UserModel.builder().id(userEntity.getId()).name(userEntity.getName())
                .surname(userEntity.getSurname()).email(userEntity.getEmail()).build();
        return user;
    }

    public UserEntity findByName(String name){
        return usersRepository.findByName(name);
    }

    public UserEntity saveUser(UserEntity user){
        user.setRole(Role.USER);
        return usersRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = findUserEntityByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(UserEntity user){
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return authorities;
    }

    public Optional<UserEntity> findUserById(Integer id){
        return usersRepository.findById(id);
    }

    public void setRoleAdmin(Integer id) {
        usersRepository.setRoleAdmin(id);
    }

    public void setRoleUser(Integer id) {
        usersRepository.setRoleUser(id);
    }

    public void blockUser(Integer id){
        usersRepository.blockUser(id);
    }

    public void unblockUser(Integer id){
        usersRepository.unblockUser(id);
    }

    public void delete(Integer id){
        usersRepository.deleteByUserId(id);
    }

    public boolean check(Integer id) {
        if (usersRepository.checkChanged(id)){
            usersRepository.updateChanged(id);
            return true;
        }
        return usersRepository.checkStatus(id);
    }
}

