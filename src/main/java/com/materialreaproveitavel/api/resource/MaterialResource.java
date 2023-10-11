package com.materialreaproveitavel.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.materialreaproveitavel.api.event.RecursoCriadoEvent;
import com.materialreaproveitavel.api.model.Material;
import com.materialreaproveitavel.api.repository.MaterialRepository;

@RestController
@RequestMapping("/materiais")
public class MaterialResource {
	
	@Autowired
	private MaterialRepository materialRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@GetMapping
	public List<Material> listar(){
		return materialRepository.findAll();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_MATERIAL') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Material> criar(@Valid @RequestBody Material categoria, HttpServletResponse response) {
		Material materialSalvo = materialRepository.save(categoria);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, materialSalvo.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(materialSalvo);
		
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_MATERIAL') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<Material> buscarPeloCodigo(@PathVariable Long id) {
		Material material = this.materialRepository.findById(id).orElse(null);
		return material != null ? ResponseEntity.ok(material) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_MATERIAL') and hasAuthority('SCOPE_write')")
	public void remover(@PathVariable Long id) {
		this.materialRepository.deleteById(id);
	}
	

}
