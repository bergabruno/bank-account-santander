package com.redbank.bankaccount.security;

import com.redbank.bankaccount.model.collection.Usuario;
import com.redbank.bankaccount.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        return usuario.map(CustomUserDetailsImpl::new).get();
    }
}
