package com.acme.avaliacao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.acme.avaliacao.model.Avaliacao;
import com.acme.avaliacao.repository.AvaliacaoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    public List<Avaliacao> getAllByMercadoId(Long mercadoId){
        return avaliacaoRepository.findAllByMercadoId(mercadoId);
    }
}	