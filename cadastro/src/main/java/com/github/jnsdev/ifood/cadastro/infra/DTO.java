package com.github.jnsdev.ifood.cadastro.infra;

import javax.validation.ConstraintValidatorContext;

/**
 * @Autor Jairo Nascimento
 * @Created 01/01/2022 - 18:46
 */
public interface DTO {
    default boolean isValid(ConstraintValidatorContext constraintValidatorContext) {
        return true;
    }
}
