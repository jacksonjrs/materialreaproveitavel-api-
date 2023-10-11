package com.materialreaproveitavel.api.repository.compra;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.materialreaproveitavel.api.model.Compra;
import com.materialreaproveitavel.api.repository.filter.CompraFilter;

public interface CompraRepositoryQuery {

	public Page<Compra> filtrar(CompraFilter compraFilter, Pageable pageable);
	
}