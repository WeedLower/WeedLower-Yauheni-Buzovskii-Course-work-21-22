package com.example.backend.model.convert;

import com.example.backend.entity.UserEntity;
import com.example.backend.model.UserModel;
import org.springframework.core.convert.converter.Converter;

public class ConvertUserEntityToUserModel implements Converter<UserEntity, UserModel> {
    @Override
    public UserModel convert(UserEntity source) {
        UserModel user = UserModel.builder().id(source.getId()).name(source.getName())
                .surname(source.getSurname()).email(source.getEmail())
                        .role(source.getRole()).status(source.isStatus())
                        .changed(source.isChanged()).build();
        return user;
    }
}
