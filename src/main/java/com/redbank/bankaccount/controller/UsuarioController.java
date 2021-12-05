package com.redbank.bankaccount.controller;

import com.redbank.bankaccount.model.collection.Usuario;
import com.redbank.bankaccount.model.collection.UsuarioLogin;
import com.redbank.bankaccount.repository.UsuarioRepository;
import com.redbank.bankaccount.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	UsuarioService usuarioService;

	// obterTodos - Isabella
	@GetMapping
	public ResponseEntity<List<Usuario>> obterTodos() {

		List<Usuario> lista = usuarioService.obterTodos();

		if (lista.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Usuario>>(lista, HttpStatus.OK);
	}

	// obterPorCodigo - Leticia
	@GetMapping("/{codigo}")
	public ResponseEntity<Usuario> obterPorCodigo(@PathVariable String codigo) {
		if (!usuarioRepository.existsById(codigo)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Usuario usuario = usuarioService.obterPorCodigo(codigo);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);

	}

	// salvar - Bruno
	@PostMapping("/cadastro")
	public ResponseEntity<Usuario> salvar(@RequestBody @Valid Usuario usuario) {

		Logger log = Logger.getLogger("com.redbank.bankaccount.controller");

		log.info("Iniciando a criacao do usuario no banco de dados");

		if (usuario == null) {
			log.info("Usuario que foi recebido pelo corpo, esta vazio, retorna uma BAD REQUEST");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		usuarioService.salvar(usuario);

		log.info("Usuario salvo com sucesso retornando no corpo da requisicao o Usuario e Status CREATED");
		return new ResponseEntity<>(usuario, HttpStatus.CREATED);
	}

	// atualizar - Andressa
	@PutMapping("/{codigo}")
	public ResponseEntity<Usuario> atualizar(@Valid @PathVariable String codigo, @RequestBody Usuario usuario) {
		boolean usuarioExistente = this.usuarioRepository.existsById(usuario.getCodigo());

		if (!usuarioExistente) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		usuario.setCodigo(codigo);
		usuario = usuarioService.atualizar(usuario);

		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}

	// deletar - Joseph
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> deletar(@PathVariable String codigo) {

		if (!usuarioRepository.existsById(codigo)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		usuarioService.deletar(codigo);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/login")
	public ResponseEntity<UsuarioLogin> login(@RequestBody Optional<UsuarioLogin> usuario){

		return usuarioService.logar(usuario).map( resp -> ResponseEntity.ok(resp)).
				orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<Usuario> buscarEmail(@PathVariable String email){

		Usuario user = usuarioRepository.findByEmail(email).get();

		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	}
}
