package com.github.jnsdev.ifood.cadastro.dto;

import com.github.jnsdev.ifood.cadastro.entity.Prato;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * @Autor Jairo Nascimento
 * @Created 01/01/2022 - 18:16
 */
@Mapper(componentModel = "cdi")
public interface PratoMapper {

    PratoDTO toDTO(Prato p);

    Prato toPrato(AdicionarPratoDTO dto);

    void toPrato(AtualizarPratoDTO dto, @MappingTarget Prato prato);

}
