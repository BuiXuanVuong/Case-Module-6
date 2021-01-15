package com.mvc.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class JwtResponse {
    private Long id;
    private String token;
    private String type = "Bearer";
    private String userName;
    private Collection<? extends GrantedAuthority> roles;


    public JwtResponse(String token, String userName, Collection<? extends GrantedAuthority> roles) {
        this.token = token;
        this.userName = userName;
        this.roles = roles;
    }
}
