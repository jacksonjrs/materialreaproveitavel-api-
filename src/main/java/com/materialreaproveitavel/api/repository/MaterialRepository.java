package com.materialreaproveitavel.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.materialreaproveitavel.api.model.Material;
import com.materialreaproveitavel.api.repository.material.MaterialRepositoryQuery;


public interface MaterialRepository extends JpaRepository<Material, Long>, MaterialRepositoryQuery{

}
