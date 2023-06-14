package br.com.senac.webpage.model;

import jakarta.persistence.Entity;


public class ProdutoDto {
	
	private String codigo;
	private String nome;
	private String preco;

	public ProdutoDto(String codigo, String nome, String preco) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.preco = preco;
	}

	public ProdutoDto() {
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
