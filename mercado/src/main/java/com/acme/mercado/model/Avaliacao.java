package com.acme.mercado.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Avaliacao {
    private long id;
    private long mercadoId;
    private String avaliacao;

}
