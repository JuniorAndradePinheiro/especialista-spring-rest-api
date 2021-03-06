package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;

@RestController("/cidades")
public class CidadeController {

	@Autowired
	private CadastroCidadeService cadastroCidade;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@GetMapping
	public List<Cidade> listar(){
		return cidadeRepository.findAll();
	}
	
	@GetMapping("/cidades/{cidadeId}")
	public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId) {
		Optional<Cidade> cidade = cidadeRepository.findById(cidadeId);
		
		if(cidade != null) {
			return ResponseEntity.ok(cidade.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	//Método está danddo NullPointerException
	@PostMapping("/cidades")
	public ResponseEntity<?> adicionar(@RequestBody Cidade cidade){
			
		try {
			cidade= cadastroCidade.salvar(cidade);
			return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
		}catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	//método está dando Bad Request direto
	@PutMapping("/cidades/{cidadeId}")
	public ResponseEntity<Cidade> alterar(@PathVariable Long cidadeId, @RequestBody Cidade cidade){
		Cidade cidadeAtual = cidadeRepository.findById(cidadeId).orElse(null);
		
		return ResponseEntity.ok(cidadeAtual);
	}
	
	/** TODO
		@DeleteMaping("/{cidadeID}")
	*/
	
}

