package com.example.backend.model;

import com.example.backend.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserModel {
    private int id;
    private String email;
    private String name;
    private String surname;
    private String password;
    private Role role;
}
