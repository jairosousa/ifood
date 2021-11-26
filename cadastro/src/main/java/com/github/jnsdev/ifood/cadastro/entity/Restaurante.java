package com.github.jnsdev.ifood.cadastro.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @Autor Jairo Nascimento
 * @Created 24/11/2021 - 17:47
 */
@Entity
@Table(name = "restaurante")
public class Restaurante extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public String proprietario;

    public String cnpj;

    public String nome;

    @ManyToOne
    public Localizacao localizacao;

    @CreationTimestamp
    public Date dataCriação;

    @UpdateTimestamp
    public Date dataAtualizacao;
}
