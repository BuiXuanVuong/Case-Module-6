package com.mvc.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrinciple implements UserDetails {

    private long id;
    private String userName;
    private String password;
    private String email;
    private String phone;
    private String birthday;
    private String image;

    private Collection<? extends GrantedAuthority> roles;

    public UserPrinciple(long id, String userName, String password, String email, String phone, String birthday, String image, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.image = image;
        this.roles = roles;
    }

    public UserPrinciple(long id, String userName, String password, String email, String phone, String birthday, String image) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.image = image;
    }


    public static UserPrinciple build(User user){
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName())
        ).collect(Collectors.toList());

        return new UserPrinciple(
                    user.getId(),
                user.getUserName(),
                user.getPassword(),
                user.getEmail(),
                user.getBirthday(),
                user.getPhone(),
                user.getImage(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
