package com.algaworks.algafood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository {
	
	List<Restaurante> listar();
	Restaurante buscar (Long id);
	Restaurante Salvar (Restaurante restaurante);
	void remover (Restaurante restaurante);
}