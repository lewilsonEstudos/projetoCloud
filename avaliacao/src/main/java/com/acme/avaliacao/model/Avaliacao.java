package com.acme.avaliacao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data@AllArgsConstructor@NoArgsConstructor@Builder
public class Avaliacao {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "mercado_id")
    private long mercadoId;
    private String avaliacao;

}
