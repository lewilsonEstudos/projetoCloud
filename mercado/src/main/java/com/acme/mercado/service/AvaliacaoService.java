package com.acme.mercado.service;

import com.acme.mercado.model.Avaliacao;
import com.acme.mercado.service.client.AvaliacaoClint;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class AvaliacaoService {

    private final AvaliacaoClint client;
    private static Map<Long, List<Avaliacao>> CACHE = new ConcurrentHashMap<>();

    @CircuitBreaker(name = "client", fallbackMethod = "buscarNoCache")
    public List<Avaliacao> getAllById(Long mercadoId){
        List<Avaliacao> avaliacoes = client.getById(mercadoId);
        CACHE.put(mercadoId, avaliacoes);
        return client.getById(mercadoId);
    }
    private List<Avaliacao> buscarNoCache(Long mercadoId, Throwable e){
        log.info("Buscando no Cache");
        return CACHE.getOrDefault(mercadoId,new ArrayList<>());
    }
}
