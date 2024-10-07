package com.acme.avaliacao.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.avaliacao.model.Avaliacao;
import com.acme.avaliacao.service.AvaliacaoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/")
@Slf4j@RequiredArgsConstructor
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getAvaliacao(@PathVariable Long id) {
        log.info("Get avaliacao: {}", id);
        List<Avaliacao> allByMercadoId = avaliacaoService.getAllByMercadoId(id);
        if (allByMercadoId.isEmpty()) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(allByMercadoId);
        }
    }

}
