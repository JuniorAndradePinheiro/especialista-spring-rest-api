package com.algaworks.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha> cozinhasPorNome(@RequestParam String nome){
		return cozinhaRepository.findTodasByNome(nome);
	}
	
	@GetMapping("/cozinhas/unica-por-nome")
	public Optional<Cozinha> cozinhaPorNome(String nome){
		return cozinhaRepository.findByNomeContaining(nome);
	}
	
	@GetMapping("/restaurantes/por-taxa-frete")
	public List<Restaurante> restaurantesPorTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal){
		return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}
	
//	@GetMapping("/restaurantes/por-nome")
//	public List<Restaurante> restaurantesPorNomeEidCozinha(String nome, Long idCozinha){
//		return restauranteRepository.findByNomeContainingAndCozinhaId(nome, idCozinha);	
	
	@GetMapping("/restaurantes/por-nome")
	public List<Restaurante> restaurantesPorNomeEidCozinha(String nome, Long idCozinha){
		return restauranteRepository.consultarPorNome(nome, idCozinha);	
	}
}
