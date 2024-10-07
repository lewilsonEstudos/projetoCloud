package com.acme.produtos.controler;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.produtos.model.Produto;
import com.acme.produtos.service.ProdutoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class ProdutoController {

	@Autowired
    private ProdutoService produtoService;

    @GetMapping // Endpoint para obter todos os produtos
    public ResponseEntity<List<Produto>> getAll() {
        List<Produto> produtos = produtoService.getAll();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}") // Endpoint para obter um produto específico
    public ResponseEntity<Produto> getById(@PathVariable Long id) {
        log.info("Buscando produto por ID: {}", id);
        Optional<Produto> optional = produtoService.getById(id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get()); // Retorna o produto encontrado
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 se não encontrado
        }
    }
}