package com.materialreaproveitavel.api.repository.compra;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import com.materialreaproveitavel.api.model.Compra;
import com.materialreaproveitavel.api.model.Compra_;
import com.materialreaproveitavel.api.repository.filter.CompraFilter;


public class CompraRepositoryImpl implements CompraRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Compra> filtrar(CompraFilter compraFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Compra> criteria = builder.createQuery(Compra.class);
		Root<Compra> root = criteria.from(Compra.class);
		
		Predicate[] predicates = criarRestricoes(compraFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Compra> query = manager.createQuery(criteria);
		
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(compraFilter));
	}

	private Predicate[] criarRestricoes(CompraFilter compraFilter, CriteriaBuilder builder,
			Root<Compra> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(!ObjectUtils.isEmpty(compraFilter.getStatus())) {
			predicates.add(builder.like(builder.upper(
					root.get(Compra_.status).as(String.class)), "%"+compraFilter.getStatus().toUpperCase() +"%"));	
			
		}
		
		
		if (compraFilter.getDataCriacaoDe() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get(Compra_.dataCriacao), compraFilter.getDataCriacaoDe()));
		}
		
		if (compraFilter.getDataCriacaoAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(Compra_.dataCriacao), compraFilter.getDataCriacaoAte()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void adicionarRestricoesDePaginacao(TypedQuery<Compra> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private Long total(CompraFilter lancamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Compra> root = criteria.from(Compra.class);
		
		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}