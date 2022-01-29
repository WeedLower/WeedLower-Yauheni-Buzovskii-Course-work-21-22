package com.example.backend.security;

public class SecurityJwtConstants {
    public static final long ACCESS_TOKEN_VALIDITY_SECOND = 100*60*60;
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES_KEY = "scopes";
}
