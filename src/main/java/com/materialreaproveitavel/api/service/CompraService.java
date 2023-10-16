package com.materialreaproveitavel.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
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
	
	
	public Compra atualizar(Long codigo, Compra compra) {
		Compra compraSalvo = buscarCompraExistente(codigo);
		if (!compra.getPessoa().equals(compraSalvo.getPessoa())) {
			validarPessoa(compra);
		}

		BeanUtils.copyProperties(compra, compraSalvo, "codigo");

		return compraRepository.save(compraSalvo);
	}

	private void validarPessoa(Compra compra) {
		Optional<Pessoa> pessoa = null;
		if (compra.getPessoa().getId() != null) {
			pessoa = pessoaRepository.findById(compra.getPessoa().getId());
		}

		if (pessoa.isEmpty() || pessoa.get().isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
	}

	private Compra buscarCompraExistente(Long codigo) {
/* 		Optional<Compra> compraSalvo = compraRepository.findById(codigo);
		if (compraSalvo.isEmpty()) {
			throw new IllegalArgumentException();
		} */
		return compraRepository.findById(codigo).orElseThrow(() -> new IllegalArgumentException());
	}	
	
	
}