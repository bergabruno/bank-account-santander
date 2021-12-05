package com.redbank.bankaccount.model.collection;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Document(collection = "tb_usuario")
public class Usuario {

	@Id
	private String codigo;

	@NotBlank
	private String nome;

	@NotBlank
	private String sobrenome;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull
	private Date dataNascimento;

	@NotBlank
	@Email(message = "Email em formato invalido")
	private String email;

	@NotBlank
	@CPF(message = "CPF em formato invalido")
	private String cpf;

	@Valid
	private Endereco endereco;

	private Double salario;

	@Valid
	private Conta conta;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;

	@NotBlank
	private String senha;

	@NotBlank
	private String telefone;


	public Usuario(String codigo, String nome, String sobrenome, Date dataNascimento, String email,
				   String cpf, Endereco endereco, Double salario, Conta conta,String senha, String telefone) {
		this.codigo = codigo;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.cpf = cpf;
		this.endereco = endereco;
		this.salario = salario;
		this.conta = conta;
		this.senha = senha;
		this.telefone = telefone;
	}

	public Usuario() {
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
