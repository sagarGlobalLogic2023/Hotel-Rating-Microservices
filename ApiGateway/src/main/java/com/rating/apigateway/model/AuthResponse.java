package com.rating.apigateway.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String userId;
    private String refreshToken;
    private String accessToken;
    private long expireAt;
    private Collection<String> authorities;
}
