package com.github.jnsdev.ifood.cadastro.dto;

import java.math.BigDecimal;

/**
 * @Autor Jairo Nascimento
 * @Created 01/01/2022 - 18:16
 */
public class PratoDTO {
    public Long id;

    public String nome;

    public String descricao;

    public RestauranteDTO restaurante;

    public BigDecimal preco;
}
