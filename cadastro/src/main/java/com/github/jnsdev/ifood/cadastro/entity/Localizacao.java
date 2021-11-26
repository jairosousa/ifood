package com.github.jnsdev.ifood.cadastro.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

/**
 * @Autor Jairo Nascimento
 * @Created 24/11/2021 - 17:51
 */

@Entity
@Table(name = "localizacao")
public class Localizacao extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public Double latitude;

    public Double longityde;

}
