package com.materialreaproveitavel.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.materialreaproveitavel.api.exception.PessoaInexistenteOuInativaException;
import com.materialreaproveitavel.api.model.Compra;
import com.materialreaproveitavel.api.model.Pessoa;
import com.materialreaproveitavel.api.repository.CompraRepository;
import com.materialreaproveitavel.api.repository.PessoaRepository;

@Service
public class CompraService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired 
	private CompraRepository compraRepository;

	public Compra salvar(Compra compra) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(compra.getPessoa().getId());
		if (pessoa.isEmpty() || pessoa.get().isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		
		return compraRepository.save(compra);
	}
	
}