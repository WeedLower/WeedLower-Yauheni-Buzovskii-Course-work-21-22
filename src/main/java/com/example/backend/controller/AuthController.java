package com.example.backend.controller;

import com.example.backend.entity.Role;
import com.example.backend.entity.UserEntity;
import com.example.backend.model.*;
import com.example.backend.model.convert.ConvertUserToAuthUser;
import com.example.backend.security.TokenProvider;
import com.example.backend.service.UsersServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UsersServise userService;
    private final TokenProvider tokenProvide;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UsersServise userService, TokenProvider tokenProvide, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenProvide = tokenProvide;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value = "/generate-token",method = RequestMethod.POST)
    public ResponseEntity generateToken(@RequestBody UserModel userModel){
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userModel.getEmail(),
                        userModel.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvide.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }

    @GetMapping("/user")
    public ResponseEntity<AuthUser> authUser(Principal user){
        ConvertUserToAuthUser convertUserToAuthUser = new ConvertUserToAuthUser();
        UserEntity userModel = userService.findByEmail(user.getName());
        if (userModel.isStatus()){return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(convertUserToAuthUser.convert(userModel));
    }

    @RequestMapping(value = "/sign-up",method = RequestMethod.POST)
    public ResponseEntity regNewUser(@RequestBody UserEntity user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        UserEntity userResult = userService.saveUser(user);
        if (userResult==null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}
