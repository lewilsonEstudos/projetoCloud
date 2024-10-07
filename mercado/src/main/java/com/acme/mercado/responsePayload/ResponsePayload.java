package com.acme.mercado.responsePayload;

import com.acme.mercado.model.Avaliacao;
import com.acme.mercado.model.Mercado;

import java.util.List;

public record ResponsePayload(Mercado mercado, List<Avaliacao> avaliacoes) {
}