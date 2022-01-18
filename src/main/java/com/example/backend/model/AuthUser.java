package com.example.backend.model;

import com.example.backend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthUser {
    private int id;
    private String name;
    private String surname;
    private String email;
    private Role role;

}
