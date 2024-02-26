package com.materialreaproveitavel.api.repository.projection;

import java.math.BigDecimal;

public class ResumoMaterial {
	
	
		
	public String nome;
	
	public String descricao;
	
	public BigDecimal preco;
	
	public String vendedor;
	
	

	public ResumoMaterial(String nome, String descricao, BigDecimal preco, String vendedor) {
		super();

		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.vendedor = vendedor;

	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}


	public String getVendedor() {
		return vendedor;
	}


	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}


}
