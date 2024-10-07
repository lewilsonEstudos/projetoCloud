package com.acme.produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.produtos.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}