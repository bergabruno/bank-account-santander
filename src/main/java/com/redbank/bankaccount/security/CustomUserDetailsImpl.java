package com.redbank.bankaccount.security;

import com.redbank.bankaccount.model.collection.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetailsImpl implements UserDetails {

    private String email; //cpf?
    private String senha;
    private List<GrantedAuthority> authorities;

    public CustomUserDetailsImpl(Usuario usuario) {
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
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
