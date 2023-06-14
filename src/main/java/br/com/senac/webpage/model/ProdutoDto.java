package br.com.senac.webpage.model;

import jakarta.persistence.Entity;


public class ProdutoDto {
	
	private String codigo;
	private String nome;
	private String descricao;
	private String quantidade;
	private String avaliacao;
	private String preco;
	private String situacao;
	private String linkImg;


	public ProdutoDto(String codigo, String nome, String descricao, String quantidade, String avaliacao, String preco,
			String situacao, String linkImg) {
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.avaliacao = avaliacao;
		this.preco = preco;
		this.situacao = situacao;
		this.linkImg = linkImg;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(String avaliacao) {
		this.avaliacao = avaliacao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getLinkImg() {
		return linkImg;
	}

	public void setLinkImg(String linkImg) {
		this.linkImg = linkImg;
	}
}
