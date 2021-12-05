package com.redbank.bankaccount.model.collection;

import javax.validation.constraints.NotBlank;

public class Endereco {

	@NotBlank
	private String rua;

	@NotBlank
	private String numero;

	private String complemento;

	@NotBlank
	private String cidade;

	@NotBlank
	private String estado;

	@NotBlank
	private String cep;

	@NotBlank
	private String bairro;

	public Endereco(String rua, String numero, String complemento,
					String cidade, String estado, String cep, String bairro) {
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
}
