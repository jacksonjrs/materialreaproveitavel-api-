package com.materialreaproveitavel.api.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ResumoCompra {
	
	
		
	public String nome;
	
	public String descricao;
	
	public BigDecimal preco;
	
	public String vendedor;
	
	private LocalDate dataCriacao;
	
	private LocalDate dataEntrega;
	
	

	public ResumoCompra(String nome, String descricao, LocalDate dataCriacao, LocalDate dataEntrega, BigDecimal preco, String vendedor) {
		super();

		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.vendedor = vendedor;
		this.dataCriacao = dataCriacao;
		this.dataEntrega = dataEntrega;

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


	public LocalDate getDataCriacao() {
		return dataCriacao;
	}


	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}


	public LocalDate getDataEntrega() {
		return dataEntrega;
	}


	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}


}
