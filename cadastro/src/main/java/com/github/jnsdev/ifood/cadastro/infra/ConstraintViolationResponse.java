package com.github.jnsdev.ifood.cadastro.infra;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Autor Jairo Nascimento
 * @Created 01/01/2022 - 19:05
 */
public class ConstraintViolationResponse {
    private final List<ConstraintViolationImpl> violacoes = new ArrayList<>();

    private ConstraintViolationResponse(ConstraintViolationException exception) {
        exception.getConstraintViolations().forEach(violation -> violacoes.add(ConstraintViolationImpl.of(violation)));
    }

    public static ConstraintViolationResponse of(ConstraintViolationException exception) {
        return new ConstraintViolationResponse(exception);
    }

    public List<ConstraintViolationImpl> getViolacoes() {
        return violacoes;
    }
}
