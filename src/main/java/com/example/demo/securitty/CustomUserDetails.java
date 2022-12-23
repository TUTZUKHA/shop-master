package com.example.demo.securitty;

import com.example.demo.entity.Profile;
import com.example.demo.type.ProfileRole;
import com.example.demo.type.ProfileStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.enabled;

public class CustomUserDetails implements UserDetails {
    private Integer id;
    private String email;
    private String password;
    private ProfileStatus status;
    private ProfileRole role;
    private List<GrantedAuthority>authorityList;

    public CustomUserDetails(Profile profile){
        this.id = profile.getId();
        this.email = profile.getName();
        this.password = profile.getPassword();
        this.status = profile.getProfileStatus();
        this.role = profile.getProfileRole();

        this.authorityList = List.of(new SimpleGrantedAuthority(role.toString()));

    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        System.out.println("CustomUserDetails : getPassword");
        return password;
    }

    @Override
    public String getUsername() {
        System.out.println("CustomUserDetails:getUsername");
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
    @Override
    public String toString() {
        return "CustomUserDetails{" +
                "id=" + id +
                ", username='" + getUsername() + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", role='" + role + '\'' +
                ", authorityList=" + authorityList +
                '}';
    }

    public int getId() {
        return id;
    }
}
