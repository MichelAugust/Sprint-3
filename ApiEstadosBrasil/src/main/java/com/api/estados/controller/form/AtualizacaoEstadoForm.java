package com.api.estados.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.api.estados.model.Estados;
import com.api.estados.model.Regioes;
import com.api.estados.repository.EstadoRepository;

public class AtualizacaoEstadoForm {
	@NotNull
	@NotEmpty
	private String nome;
	@NotNull
	private Regioes regiao;
	@NotNull
	private Long populacao;
	@NotNull
	@NotEmpty
	private String capital;
	@NotNull
	private Double area;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Regioes getRegiao() {
		return regiao;
	}

	public void setRegiao(Regioes regiao) {
		this.regiao = regiao;
	}

	public Long getPopulacao() {
		return populacao;
	}

	public void setPopulacao(Long populacao) {
		this.populacao = populacao;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public Estados atualizar(Long id, EstadoRepository estadoRepository) {
		Estados estado = estadoRepository.getById(id);
		estado.setNome(this.nome);
		estado.setRegiao(this.regiao);
		estado.setPopulacao(this.populacao);
		estado.setCapital(this.capital);
		estado.setArea(this.area);

		return estado;
	}
}