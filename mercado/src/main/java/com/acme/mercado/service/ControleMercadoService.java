package com.acme.mercado.service;

import com.acme.mercado.model.Mercado;
import com.acme.mercado.service.rabbit.ControleMercadoProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ControleMercadoService {

    private final ControleMercadoProducer controleMercadoProducer;
    private final MercadoService mercadoService;

    public void controle(Long id) throws JsonProcessingException {
        Optional<Mercado> mercadoOptional = mercadoService.findById(id);

        if (mercadoOptional.isEmpty()) {
            throw new IllegalArgumentException("Mercado inválido, não consta em nossa base de dados");
        }

        Mercado mercado = mercadoOptional.get();
        controleMercadoProducer.send(mercado);
    }
}