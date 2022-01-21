package com.example.backend.model.convert;

import com.example.backend.entity.UserEntity;
import com.example.backend.model.AuthUser;
import org.springframework.core.convert.converter.Converter;

public class ConvertUserToAuthUser implements Converter<UserEntity, AuthUser> {
    @Override
    public AuthUser convert(UserEntity user) {
        AuthUser authUser = new AuthUser();
        authUser.setId(user.getId());
        authUser.setEmail(user.getEmail());
        authUser.setName(user.getName());
        authUser.setSurname(user.getSurname());
        authUser.setRole(user.getRole());
        return authUser;
    }
}
