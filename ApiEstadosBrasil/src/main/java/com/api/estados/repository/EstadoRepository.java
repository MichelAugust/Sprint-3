package com.api.estados.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.estados.model.Estados;
import com.api.estados.model.Regioes;

public interface EstadoRepository extends JpaRepository<Estados, Long> {
	Page<Estados> findByRegiao(Regioes regiao, PageRequest pageable);

}