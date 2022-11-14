package com.api.estados.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.api.estados.controller.dto.EstadoDto;
import com.api.estados.controller.form.AtualizacaoEstadoForm;
import com.api.estados.controller.form.EstadoForm;
import com.api.estados.model.Estados;
import com.api.estados.model.Regioes;
import com.api.estados.repository.EstadoRepository;

@RestController
@RequestMapping("/api/states")
public class EstadosController {

	@Autowired
	private EstadoRepository estadoRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<EstadoDto> cadastrar(@RequestBody @Valid EstadoForm form, UriComponentsBuilder uriBuilder) {
		Estados estado = form.converter();
		estadoRepository.save(estado);

		URI uri = uriBuilder.path("/api/states/{id}").buildAndExpand(estado.getId()).toUri();
		return ResponseEntity.created(uri).body(new EstadoDto(estado));
	}

	@GetMapping
	public List<EstadoDto> listar(@RequestParam(required = false) Regioes regiao,
			@RequestParam(required = false) String ordenar) {

		Sort sort = Sort.by("id").ascending();
		PageRequest pageable = PageRequest.of(0, 27, sort);

		if (ordenar != null) {
			if (ordenar.equals("populacao")) {
				sort = Sort.by("populacao").descending();
				pageable = PageRequest.of(0, 27, sort);
			} else if (ordenar.equals("area")) {
				sort = Sort.by("area").descending();
				pageable = PageRequest.of(0, 27, sort);
			}
		}
		if (regiao == null) {
			Page<Estados> estados = estadoRepository.findAll(pageable);
			return EstadoDto.converter(estados).getContent();
		} else {
			Page<Estados> estados = estadoRepository.findByRegiao(regiao, pageable);
			return EstadoDto.converter(estados).getContent();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<EstadoDto> detalhar(@PathVariable Long id) {

		Optional<Estados> estado = estadoRepository.findById(id);
		if (estado.isPresent()) {
			return ResponseEntity.ok(new EstadoDto(estado.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<EstadoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoEstadoForm form) {
		Optional<Estados> optional = estadoRepository.findById(id);
		if (optional.isPresent()) {
			Estados estado = form.atualizar(id, estadoRepository);
			return ResponseEntity.ok(new EstadoDto(estado));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Optional<Estados> optional = estadoRepository.findById(id);
		if (optional.isPresent()) {
			estadoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
