package com.algaworks.brewer.model;


import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class Cerveja {
	
	@NotBlank(message = "O SKU é um campo obrigatório!")
	private String sku;
	
	@NotBlank(message = "O nome é um campo obrigatório!")
	private String nome;
	
	@NotBlank(message = "A descrição é um campo obrigatório!")
	@Size(max = 50,min = 1, message="O tamanho da descrição deve estar entre 1 e 50")
	private String descricao;
	
	
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getNome() {
		return nome;
	}
	
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
