package com.github.jnsdev.ifood.cadastro.dto;

import com.github.jnsdev.ifood.cadastro.entity.Restaurante;
import com.github.jnsdev.ifood.cadastro.infra.DTO;
import com.github.jnsdev.ifood.cadastro.infra.ValidDTO;

import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @Autor Jairo Nascimento
 * @Created 01/01/2022 - 17:57
 */
@ValidDTO
public class AdicionarRestauranteDTO implements DTO {

    @NotEmpty
    @NotNull
    public String proprietario;

    @Pattern(regexp = "[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}\\/[0-9]{4}\\-[0-9]{2}", message = "o campo deve ter o padrão: 00.000.000/0000-00")
    @NotNull
    public String cnpj;

    @Size(min = 3, max = 30)
    public String nomeFantasia;

    public LocalizacaoDTO localizacao;

    @Override
    public boolean isValid(ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        if (Restaurante.find("cnpj", cnpj).count() > 0) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("CNPJ já cadastrado")
                    .addPropertyNode("cnpj")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
