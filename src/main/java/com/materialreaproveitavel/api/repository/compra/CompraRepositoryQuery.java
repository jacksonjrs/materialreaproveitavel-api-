package com.materialreaproveitavel.api.repository.compra;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.materialreaproveitavel.api.model.Compra;
import com.materialreaproveitavel.api.repository.filter.CompraFilter;
import com.materialreaproveitavel.api.repository.projection.ResumoCompra;

public interface CompraRepositoryQuery {

	public Page<Compra> filtrar(CompraFilter compraFilter, Pageable pageable);
	public Page<ResumoCompra> resumir(CompraFilter compraFilter, Pageable pageable);
	
}