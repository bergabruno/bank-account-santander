package com.redbank.bankaccount.repository;

import com.redbank.bankaccount.model.collection.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    Optional<Usuario> findByEmail(String email);

    Optional<Object> findByCpf(String cpf);

    Optional<Usuario> findByTelefone(String telefone);

}
