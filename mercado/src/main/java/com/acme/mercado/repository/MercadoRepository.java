package com.acme.mercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.mercado.model.Mercado;

@Repository
public interface MercadoRepository  extends JpaRepository<Mercado, Long>{

}