package com.acme.mercado.service.client;

import com.acme.mercado.model.Avaliacao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("AVALIACAO-SERVICE")
public interface AvaliacaoClint {

    @GetMapping("/{id}")
    List<Avaliacao> getById(@PathVariable Long id);
}
