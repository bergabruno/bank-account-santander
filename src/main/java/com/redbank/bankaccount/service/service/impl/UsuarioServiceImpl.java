package com.redbank.bankaccount.service.service.impl;

import com.redbank.bankaccount.model.collection.Conta;
import com.redbank.bankaccount.model.collection.ContaCorrente;
import com.redbank.bankaccount.model.collection.Usuario;
import com.redbank.bankaccount.model.collection.UsuarioLogin;
import com.redbank.bankaccount.repository.UsuarioRepository;
import com.redbank.bankaccount.service.UsuarioService;
import com.redbank.bankaccount.valid.Validar;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	// obterTodos - Isabella
	@Override
	public List<Usuario> obterTodos() {
		return usuarioRepository.findAll();
	}

	// obterPorCodigo - Leticia
	@Override
	public Usuario obterPorCodigo(String codigo) {
		return this.usuarioRepository.findById(codigo).get();
	}

	// salvar - Bruno
	@Override
	public Usuario salvar(Usuario usuario) {

		Logger log = Logger.getLogger("com.redbank.bankaccount.controller");

		Validar validar = new Validar();

		validar.validarSenha(usuario.getSenha());

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String cripto = encoder.encode(usuario.getSenha());

		usuario.setSenha(cripto);

		validarDados(usuario);

		if (usuario.getSalario() == null || usuario.getSalario() < 0) {
			usuario.setSalario(0.0);
		}

		// setando a data de cadastro
		usuario.setDataCadastro(LocalDate.now());

		// TODO contaCorrente e contaPoupanca, receber dado do front
		Conta conta = new ContaCorrente("0001", usuario.getConta().getConta(), usuario.getConta().getSaldo(),
				"Corrente");
		usuario.setConta(conta);

		return usuarioRepository.save(usuario);
	}

	// atualizar - Andressa
	@Override
	public Usuario atualizar(Usuario usuario) {
		return this.usuarioRepository.save(usuario);
	}

	// deletar - Joseph
	@Override
	public void deletar(String codigo) {
		this.usuarioRepository.deleteById(codigo);

	}

	@Override
	public Optional<UsuarioLogin> logar(Optional<UsuarioLogin> usuario) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		Optional<Usuario> user = usuarioRepository.findByEmail(usuario.get().getEmail());

		if (user.isPresent()) {
			if (encoder.matches(usuario.get().getSenha(), user.get().getSenha())) {

				String auth = usuario.get().getEmail() + ":" + usuario.get().getSenha();

				byte[] encondedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.UTF_8));
				String authHeader = "Basic " + new String(encondedAuth);

				usuario.get().setToken(authHeader);
				usuario.get().setCodigo(user.get().getCodigo());
				usuario.get().setSenha(user.get().getSenha());
				usuario.get().setNome(user.get().getNome());

				return usuario;
			}
		} else {
			throw new RuntimeException("Usuario não encontrado");
		}

		return null;
	}

	public void validarDados(Usuario user) {

		if (user == null) {
			throw new RuntimeException("ué?");
		}
		boolean cpfEmUso = usuarioRepository.findByCpf(user.getCpf()).stream()
				.anyMatch(usuarioExistente -> !usuarioExistente.equals(user));

		boolean telefoneEmUso = usuarioRepository.findByTelefone(user.getTelefone()).stream()
				.anyMatch(usuarioExistente -> !usuarioExistente.equals(user));

		boolean emailEmUso = usuarioRepository.findByEmail(user.getEmail()).stream()
				.anyMatch(usuarioExistente -> !usuarioExistente.equals(user));

		if (emailEmUso) {
			throw new RuntimeException("Email ja esta em uso!");
		}

		if (telefoneEmUso) {
			throw new RuntimeException("Telefone ja esta em uso!");
		}

		if (cpfEmUso) {
			throw new RuntimeException("CPF ja esta em uso!");
		}
	}

}
