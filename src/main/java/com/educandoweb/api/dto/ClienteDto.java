package com.educandoweb.api.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.educandoweb.api.domain.Cliente;

public class ClienteDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatorio")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120")
	private String nome;
	
	@NotEmpty(message="Preenchimento obrigatorio")
	@NotNull
	@Email(message="Email invalido")
	private String email;

	public ClienteDto() {}
	
	public ClienteDto(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
