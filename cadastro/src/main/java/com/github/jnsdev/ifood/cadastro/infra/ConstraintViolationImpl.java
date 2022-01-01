package com.github.jnsdev.ifood.cadastro.infra;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.ConstraintViolation;
import java.io.Serializable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Autor Jairo Nascimento
 * @Created 01/01/2022 - 19:14
 */
public class ConstraintViolationImpl implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "Path do atributo, ex: dataInicio, pessoa.endereco.numero", required = false)
    private final String atributo;

    @Schema(description = "Mensagem descritiva do erro possivelmente associado ao path", required = true)
    private final String mensagem;

    private ConstraintViolationImpl(ConstraintViolation<?> violation) {
        this.mensagem = violation.getMessage();
        this.atributo = Stream.of(violation.getPropertyPath().toString().split("\\.")).skip(2).collect(Collectors.joining("."));
    }

    public ConstraintViolationImpl(String atributo, String mensagem) {
        this.mensagem = mensagem;
        this.atributo = atributo;
    }

    public static ConstraintViolationImpl of(ConstraintViolation<?> violation) {
        return new ConstraintViolationImpl(violation);
    }

    public static ConstraintViolationImpl of(String violation) {
        return new ConstraintViolationImpl(null, violation);
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getAtributo() {
        return atributo;
    }
}
