package com.acme.produtos.model;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor@Builder
@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String marca;
	private BigDecimal valor;

	@Transient // Este campo não será persistido no banco de dados
	private BigDecimal valorComTaxa;


	public void calcularValorComTaxa(BigDecimal taxa) {
		this.valorComTaxa = valor.add(valor.multiply(taxa));
	}
}