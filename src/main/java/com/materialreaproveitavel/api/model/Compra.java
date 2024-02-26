package com.materialreaproveitavel.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "compra")
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name = "sub_total")
	private BigDecimal subTotal;
	
	@NotNull
	@Column(name = "taxa_frete")
	private BigDecimal taxaFrete;
	
	@NotNull
	@Column(name = "valor_total")
	private BigDecimal valorTotal;
	
	@NotNull
	@Column(name = "data_criacao")
	private LocalDate dataCriacao;
	
	@Column(name = "data_confirmacao")
	private LocalDate dataConfirmacao;
	
	@Column(name = "data_entrega")
	private LocalDate dataEntrega;
	
	@Column(name = "data_cancelamento")
	private LocalDate dataCancelamento;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusPedido status;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_material")
	private Material material;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_comprador")
	private Pessoa comprador;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubtotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getTaxaFrete() {
		return taxaFrete;
	}

	public void setTaxaFrete(BigDecimal taxaFrete) {
		this.taxaFrete = taxaFrete;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDate getDataConfirmacao() {
		return dataConfirmacao;
	}

	public void setDataConfirmacao(LocalDate dataConfirmacao) {
		this.dataConfirmacao = dataConfirmacao;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public LocalDate getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(LocalDate dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}	
	
	public Pessoa getComprador() {
		return comprador;
	}

	public void setComprador(Pessoa comprador) {
		this.comprador = comprador;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compra other = (Compra) obj;
		return Objects.equals(id, other.id);
	}
	

}
