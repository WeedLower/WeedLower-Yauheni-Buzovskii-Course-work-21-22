package com.example.backend.security;

import org.springframework.beans.factory.annotation.Value;

public class SecurityJwtConstants {
    public static final long ACCESS_TOKEN_VALIDITY_SECOND = 100*60*60;

    @Value("${auth.secret}")
    public static String SIGNING_KEY;
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES_KEY = "scopes";
}
