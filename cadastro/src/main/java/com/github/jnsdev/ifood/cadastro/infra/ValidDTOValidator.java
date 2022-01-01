package com.github.jnsdev.ifood.cadastro.infra;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Autor Jairo Nascimento
 * @Created 01/01/2022 - 18:48
 */
public class ValidDTOValidator implements ConstraintValidator<ValidDTO, DTO> {
    @Override
    public void initialize(ValidDTO constraintAnnotation) {
    }

    @Override
    public boolean isValid(DTO dto, ConstraintValidatorContext constraintValidatorContext) {
        return dto.isValid(constraintValidatorContext);
    }
}
