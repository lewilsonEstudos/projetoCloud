package com.acme.produtos.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.acme.produtos.model.Produto;
import com.acme.produtos.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {


    //semLucro: 0.10
//poucoLucro: 0.20
//medioLucro: 0.50
//muitoLucro: 1.00

    @Value("${lucro.semLucro}")
    private BigDecimal semLucro;
    @Value("${lucro.poucoLucro}")
    private BigDecimal poucoLucro;
    @Value("${lucro.medioLucro}")
    private BigDecimal medioLucro;
    @Value("${lucro.muitoLucro}")
    private BigDecimal muitoLucro;

	@Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> getAll() {
        BigDecimal taxa = getTaxas();
        List<Produto> produtos = produtoRepository.findAll();
        produtos.forEach(produto -> produto.calcularValorComTaxa(taxa)); // Calcula o valor com taxa para cada produto
        return produtos; // Retorna a lista de produtos
    }

    public Optional<Produto> getById(long id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isPresent()) {
            Produto produto = produtoOptional.get();
            BigDecimal taxa = getTaxas();
            produto.calcularValorComTaxa(taxa); // Calcula e atualiza o valor com a taxa
            return Optional.of(produto); // Retorna um Optional com o Produto
        } else {
            return Optional.empty(); // Retorna um Optional vazio se não encontrado
        }
    }

    private BigDecimal calcularValorComTaxa(Produto produto, BigDecimal taxa) {
        return produto.getValor().add(produto.getValor().multiply(taxa));
    }

    private BigDecimal getTaxas() {
        Random random = new Random();
        int valorAleatorio = random.nextInt(4); // Gera um número aleatório de 0 a 3

        return switch (valorAleatorio) {
            case 0 -> semLucro;
            case 1 -> poucoLucro;
            case 2 -> medioLucro;
            case 3 -> muitoLucro;
            default -> semLucro;
        };
    }
}
