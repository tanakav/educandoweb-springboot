package com.educandoweb.api.dto;

import java.io.Serializable;

import com.educandoweb.api.domain.Produto;

public class ProdutoDto implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nome;

	private Double preco;
	
	public ProdutoDto() {
		
	}
	
	public ProdutoDto(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.preco = produto.getPreco();
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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	


}
