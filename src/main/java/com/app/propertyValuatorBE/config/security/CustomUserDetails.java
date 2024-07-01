package com.app.propertyValuatorBE.config.security;

import com.app.propertyValuatorBE.db.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Getter
    private String id;
    @Getter
    private String username;
    @Getter
    @Setter
    private String roleId;
    @Getter
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(String id, String username, String password,
                           Collection<? extends GrantedAuthority> authorities, String roleId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.roleId = roleId;
    }

    public static CustomUserDetails build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().toString())).collect(Collectors.toList());
        String roleId = user.getRoles().stream().findFirst().get().getId();
        return new CustomUserDetails(user.getId(), user.getUsername(), user.getPassword(), authorities, roleId);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Checks if is account non locked.
     *
     * @return true, if is account non locked
     */
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CustomUserDetails user = (CustomUserDetails) o;
        return Objects.equals(id, user.id);
    }
}