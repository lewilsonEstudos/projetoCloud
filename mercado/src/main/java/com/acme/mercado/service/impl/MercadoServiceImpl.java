package com.acme.mercado.service.impl;

import java.util.List;
import java.util.Optional;

import com.acme.mercado.model.Mercado;
import com.acme.mercado.repository.MercadoRepository;
import com.acme.mercado.service.MercadoService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MercadoServiceImpl implements MercadoService{

	@Autowired
	private MercadoRepository mercadoRepository;
	
	
	@Override
	public Mercado create(Mercado mercado) {
		// TODO Auto-generated method stub
		return mercadoRepository.save(mercado);
	}

	@Override
	public Optional<Mercado> findById(long id) {
		// TODO Auto-generated method stub
		return mercadoRepository.findById(id);
	}

	@Override
	public List<Mercado> findAll() {
		// TODO Auto-generated method stub
		return mercadoRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		mercadoRepository.deleteById(id);
	}

	@Override
	public Mercado update(Mercado mercado) {
		// TODO Auto-generated method stub
		return mercadoRepository.save(mercado);
	}
}