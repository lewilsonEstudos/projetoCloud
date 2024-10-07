package com.acme.mercado.controler;

import java.util.List;
import java.util.Optional;

import com.acme.mercado.model.Avaliacao;
import com.acme.mercado.responsePayload.ResponsePayload;
import com.acme.mercado.service.AvaliacaoService;
import com.acme.mercado.service.ControleMercadoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;


import com.acme.mercado.model.Mercado;
import com.acme.mercado.service.MercadoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class MercadoController {

	private final MercadoService mercadoService;
    private final AvaliacaoService avaliacaoService;

    private final ControleMercadoService controleMercadoService;

    @GetMapping
	public ResponseEntity<?> findAll(){
		return  ResponseEntity.ok(mercadoService.findAll());
	}

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        log.info("Find mercado by ID {}", id);
        Optional<Mercado> optMercado = mercadoService.findById(id);
        if (optMercado.isPresent()) {
            return ResponseEntity.ok(optMercado.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/complete")
    public ResponseEntity<?> findByIdComAvaliacoes(@PathVariable Long id) {
        log.info("Find Mercado by ID {}", id);

        Optional<Mercado> optMercado = mercadoService.findById(id);

        if (optMercado.isPresent()) {
            List<Avaliacao> allById = avaliacaoService.getAllById(id);
            ResponsePayload responsePayload = new ResponsePayload(optMercado.get(),allById);
            return ResponseEntity.ok(responsePayload);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/controleMercado")
    public ResponseEntity<Void> processControleMercado(@PathVariable Long id) {
        try {
            controleMercadoService.controle(id);
            return ResponseEntity.ok().build();
        } catch (JsonProcessingException e) {
            log.error("Erro ao processar controle de mercado para ID: {}", id, e);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Erro inesperado ao processar controle de mercado para ID: {}", id, e);
            return ResponseEntity.ok().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        log.info("Buscando Mercado por ID: {}", id);
    	mercadoService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Mercado mercado) {
        Mercado saved = mercadoService.create(mercado);
        return ResponseEntity.ok(saved);
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Mercado mercado) {
    	mercadoService.update(mercado);
        return ResponseEntity.ok().build();
    }
}