package com.classifier.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

public class UserNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Nome obrigatório")
	private String name;

	@CPF(message = "CPF inválido")
	private String cpf;

	@NotEmpty(message= "Email obrigatório")
	@Email(message = "Email inválido")
	private String email;

	@NotEmpty(message = "Senha obrigatória")
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
