package com.github.jnsdev.ifood.cadastro.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @Autor Jairo Nascimento
 * @Created 24/11/2021 - 17:54
 */
@Entity
@Table(name = "localizacao")
public class Prato extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public String nome;

    public String descricao;

    @ManyToOne
    public Restaurante restaurante;

    public BigDecimal preco;
}
