package com.api.estados.controller.dto;

import org.springframework.data.domain.Page;

import com.api.estados.model.Estados;
import com.api.estados.model.Regioes;

public class EstadoDto {

	private Long id;
	private String nome;
	private Regioes regiao;
	private Long populacao;
	private String capital;
	private Double area;

	public EstadoDto(Estados estado) {
		this.id = estado.getId();
		this.nome = estado.getNome();
		this.regiao = estado.getRegiao();
		this.populacao = estado.getPopulacao();
		this.capital = estado.getCapital();
		this.area = estado.getArea();
	}

	public static Page<EstadoDto> converter(Page<Estados> estados) {
		return estados.map(EstadoDto::new);
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Regioes getRegiao() {
		return regiao;
	}

	public Long getPopulacao() {
		return populacao;
	}

	public String getCapital() {
		return capital;
	}

	public Double getArea() {
		return area;
	}

}