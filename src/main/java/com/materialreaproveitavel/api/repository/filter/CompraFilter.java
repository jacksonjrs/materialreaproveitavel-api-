package com.materialreaproveitavel.api.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class CompraFilter {

	private String status;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataCriacaoDe;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataCriacaoAte;



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getDataCriacaoDe() {
		return dataCriacaoDe;
	}

	public void setDataCriacaoDe(LocalDate dataCriacaoDe) {
		this.dataCriacaoDe = dataCriacaoDe;
	}

	public LocalDate getDataCriacaoAte() {
		return dataCriacaoAte;
	}

	public void setDataCriacaoAte(LocalDate dataCriacaoAte) {
		this.dataCriacaoAte = dataCriacaoAte;
	}

	
}