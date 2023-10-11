package com.materialreaproveitavel.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.materialreaproveitavel.api.model.Compra;
import com.materialreaproveitavel.api.repository.compra.CompraRepositoryQuery;


public interface CompraRepository extends JpaRepository<Compra, Long>, CompraRepositoryQuery{

}
