package com.acme.mercado.service;

import java.util.List;
import java.util.Optional;

import com.acme.mercado.model.Mercado;

public interface MercadoService {
	Mercado create(Mercado mercado);
	Optional<Mercado> findById(long id);
	List<Mercado> findAll();
	void deleteById(Long id);
	Mercado update(Mercado mercado);
}