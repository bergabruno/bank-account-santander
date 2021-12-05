package com.redbank.bankaccount.service;

import com.redbank.bankaccount.model.collection.Usuario;
import com.redbank.bankaccount.model.collection.UsuarioLogin;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public interface UsuarioService {

	// obterTodos - Isabella
	public List<Usuario> obterTodos();

	// obterPorCodigo - Leticia

	public Usuario obterPorCodigo(String codigo);

	// salvar - Bruno
	public Usuario salvar(Usuario usuario);

	// atualizar - Andressa
	public Usuario atualizar(Usuario usuario);

	// deletar - Joseph
	public void deletar(String codigo);

	public Optional<UsuarioLogin> logar(Optional<UsuarioLogin> usuario);

}
