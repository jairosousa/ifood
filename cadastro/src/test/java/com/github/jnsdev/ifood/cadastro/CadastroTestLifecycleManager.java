package com.github.jnsdev.ifood.cadastro;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * @Autor Jairo Nascimento
 * @Created 27/11/2021 - 20:36
 */
public class CadastroTestLifecycleManager implements QuarkusTestResourceLifecycleManager {

    public static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:12.2");
    @Override
    public Map<String, String> start() {
        POSTGRES.start();
        Map<String,String> propriedades = new HashMap<>();

        //Banco Dados
        propriedades.put("quarkus.datasource.db-kind", "postgresql");
        propriedades.put("quarkus.datasource.jdbc.url", POSTGRES.getJdbcUrl());
        propriedades.put("quarkus.datasource.username", POSTGRES.getUsername());
        propriedades.put("quarkus.datasource.password", POSTGRES.getPassword());
        return propriedades;
    }

    @Override
    public void stop() {
        if (POSTGRES != null && POSTGRES.isRunning()){
            POSTGRES.stop();
        }
    }
}
