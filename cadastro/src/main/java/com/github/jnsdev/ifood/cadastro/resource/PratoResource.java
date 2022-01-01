package com.github.jnsdev.ifood.cadastro.resource;

import com.github.jnsdev.ifood.cadastro.dto.AdicionarPratoDTO;
import com.github.jnsdev.ifood.cadastro.dto.AtualizarPratoDTO;
import com.github.jnsdev.ifood.cadastro.dto.PratoDTO;
import com.github.jnsdev.ifood.cadastro.dto.PratoMapper;
import com.github.jnsdev.ifood.cadastro.entity.Prato;
import com.github.jnsdev.ifood.cadastro.entity.Restaurante;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/restaurantes/{idRestaurante}/pratos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Prato")
public class PratoResource {

    @Inject
    PratoMapper pratoMapper;

    @GET
    public List<PratoDTO> buscar(@PathParam("idRestaurante") Long idRestaurante) {
        Optional<Restaurante> restauranteOp = buscarValidarRestaurante(idRestaurante);
        if (restauranteOp.isEmpty()) {
            throw new NotFoundException("Restaurante não existe");
        }
        Stream<Prato> pratos = Prato.stream("restaurante", restauranteOp.get());
        return pratos.map(p -> pratoMapper.toDTO(p)).collect(Collectors.toList());
    }

    @POST
    @Transactional
    public Response adicionar(@PathParam("idRestaurante") Long idRestaurante, AdicionarPratoDTO dto) {
        Optional<Restaurante> restauranteOp = buscarValidarRestaurante(idRestaurante);

//        //Utilizando dto, recebi detached entity passed to persist:
//        Prato prato = new Prato();
//        prato.nome = dto.nome;
//        prato.descricao = dto.descricao;
//        prato.preco = dto.preco;
//        prato.restaurante = restauranteOp.get();
//        prato.persist();

        Prato prato = pratoMapper.toPrato(dto);
        prato.restaurante = restauranteOp.get();
        prato.persist();

        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public void atualizar(@PathParam("idRestaurante") Long idRestaurante, @PathParam("id") Long id, AtualizarPratoDTO dto) {
        Optional<Restaurante> restauranteOp = buscarValidarRestaurante(idRestaurante);

        Optional<Prato> pratoOp = Prato.findByIdOptional(id);
        if (pratoOp.isEmpty()) {
            throw new NotFoundException("Prato não existe");
        }

        Prato prato = pratoOp.get();
        pratoMapper.toPrato(dto, prato);
        prato.persist();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void deletar(@PathParam("idRestaurante") Long idRestaurante, @PathParam("id") Long id) {
        Optional<Restaurante> restauranteOp = buscarValidarRestaurante(idRestaurante);

        Optional<Prato> pratoOp = Restaurante.findByIdOptional(id);

        pratoOp.ifPresentOrElse(Prato::delete,
                () -> {
                    throw new NotFoundException();
                });

    }

    private Optional<Restaurante> buscarValidarRestaurante(@PathParam("idRestaurante") Long idRestaurante) {
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
        if (restauranteOp.isEmpty()) {
            throw new NotFoundException("Restaurante não existe");
        }
        return restauranteOp;
    }

}
