package com.materialreaproveitavel.api.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.materialreaproveitavel.api.event.RecursoCriadoEvent;
import com.materialreaproveitavel.api.exception.PessoaInexistenteOuInativaException;
import com.materialreaproveitavel.api.exceptionhandler.MaterialreaproveitavelExceptionHandler.Erro;
import com.materialreaproveitavel.api.model.Compra;
import com.materialreaproveitavel.api.repository.CompraRepository;
import com.materialreaproveitavel.api.repository.filter.CompraFilter;
import com.materialreaproveitavel.api.service.CompraService;

@RestController
@RequestMapping("/compras")
public class CompraResource {
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private CompraService compraService;
	
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private CompraRepository compraRepository;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_COMPRA') and hasAuthority('SCOPE_read')" )
	public Page<Compra> pesquisar (CompraFilter compraFilter, Pageable pageable) {
		return compraRepository.filtrar(compraFilter, pageable);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_COMPRA') and hasAuthority('SCOPE_read')" )
	public ResponseEntity<Compra> buscarPeloCodigo(@PathVariable Long id) {
		Optional<Compra> compra = compraRepository.findById(id);
		return compra.isPresent() ? ResponseEntity.ok(compra.get()) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_COMPRA') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Compra> criar(@Valid @RequestBody Compra compra, HttpServletResponse response) {
		Compra compraSalvo = compraService.salvar(compra);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, compraSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(compraSalvo);
	}
	
	@DeleteMapping("/{id}")	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_COMPRA') and hasAuthority('SCOPE_write')")
	public void remover(@PathVariable Long id) {
		compraRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_COMPRA')")
	public ResponseEntity<Compra> atualizar(@PathVariable Long id, @Valid @RequestBody Compra compra) {
		try {
			Compra compraSalva = compraService.atualizar(id, compra);
			return ResponseEntity.ok(compraSalva);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ExceptionHandler({ PessoaInexistenteOuInativaException.class })
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex) {
		String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
	
}