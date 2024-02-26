package com.materialreaproveitavel.api.repository.material;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.materialreaproveitavel.api.model.Material;
import com.materialreaproveitavel.api.repository.filter.MaterialFilter;
import com.materialreaproveitavel.api.repository.projection.ResumoMaterial;

public interface MaterialRepositoryQuery {

	public Page<Material> filtrar(MaterialFilter compraFilter, Pageable pageable);
	public Page<ResumoMaterial> resumir(MaterialFilter lancamentoFilter, Pageable pageable);

}
