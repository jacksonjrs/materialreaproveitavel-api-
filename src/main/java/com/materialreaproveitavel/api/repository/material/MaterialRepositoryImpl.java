package com.materialreaproveitavel.api.repository.material;

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


import com.materialreaproveitavel.api.model.Material;
import com.materialreaproveitavel.api.model.Material_;
import com.materialreaproveitavel.api.model.Pessoa_;
import com.materialreaproveitavel.api.repository.filter.MaterialFilter;
import com.materialreaproveitavel.api.repository.projection.ResumoMaterial;

public class MaterialRepositoryImpl implements MaterialRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Material> filtrar(MaterialFilter materialFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Material> criteria = builder.createQuery(Material.class);
		Root<Material> root = criteria.from(Material.class);
		
		Predicate[] predicates = criarRestricoes(materialFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Material> query = manager.createQuery(criteria);
		
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(materialFilter));
	}

	private Predicate[] criarRestricoes(MaterialFilter materialFilter, CriteriaBuilder builder,
			Root<Material> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(!ObjectUtils.isEmpty(materialFilter.getNome())) {
			predicates.add(builder.like(builder.upper(
					root.get(Material_.nome).as(String.class)), "%"+materialFilter.getNome().toUpperCase() +"%"));	
			
		}	
		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	@Override
	public Page<ResumoMaterial> resumir(MaterialFilter materialFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoMaterial> criteria = builder.createQuery(ResumoMaterial.class);
		Root<Material> root = criteria.from(Material.class);
		
		criteria.select(builder.construct(ResumoMaterial.class
				, root.get(Material_.nome)
				, root.get(Material_.descricao)
				, root.get(Material_.preco)
				, root.get(Material_.vendedor).get(Pessoa_.nome)));
		
		Predicate[] predicates = criarRestricoes(materialFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<ResumoMaterial> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(materialFilter));
	}
	
	
		
	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private Long total(MaterialFilter materialFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Material> root = criteria.from(Material.class);
		
		Predicate[] predicates = criarRestricoes(materialFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}


}
