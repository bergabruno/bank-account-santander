package com.redbank.bankaccount.valid;

import com.redbank.bankaccount.model.collection.Usuario;
import com.redbank.bankaccount.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class Validar {

	@Autowired
	UsuarioRepository usuarioRepository;


	public  void validarSenha(String senha) {
		Logger log = Logger.getLogger("com.redbank.bankaccount.valid.Validar");

		log.info("Inicando a validacao da senha");

		if (senha.matches("([0-9])\\1+")) {
			// encontra nos numeros\1+ "match one or more instances of the previously matched digit"
			throw new RuntimeException("Nao pode colocar todos numeros iguais!");
		}

		if (!senha.matches("[0-9]+") || senha.length() != 6) {
			log.info("A senha nao passou na validacao de tamanho ou de numeros");
			throw new RuntimeException("Nao pode letras ou o tamanho foi violado!");
		}

		char[] valores = { senha.charAt(0), senha.charAt(1), senha.charAt(2), senha.charAt(3), senha.charAt(4),
				senha.charAt(5) };

		boolean senhaIncorreta = false;

		for (int i = 0; i < senha.length() - 2; i++) {

			if ( valores[i] + 1 == valores[i + 1]
					&& valores[i + 1] + 1 ==  valores[i + 2]) {
				senhaIncorreta = true;
				break;
			} else if ( valores[i] - 1 == valores[i + 1]
					&&  valores[i + 1] - 1 ==  valores[i + 2]) {
				senhaIncorreta = true;
				break;
			}
		}

		if (senhaIncorreta) {
			log.info("A senha nao passou na validacao");
			throw new RuntimeException("A senha esta com numeros consecutivos!");
		}

		log.info("Senha correta!");
	}

}
