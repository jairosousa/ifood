package com.github.jnsdev.ifood.cadastro.resource;

import com.github.jnsdev.ifood.cadastro.entity.Prato;
import com.github.jnsdev.ifood.cadastro.entity.Restaurante;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/restaurantes/{idRestaurante}/pratos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Prato")
public class PratoResource {

    @GET
    public List<Prato> buscar(@PathParam("idRestaurante") Long idRestaurante) {
        Optional<Restaurante> restauranteOp = buscarValidarRestaurante(idRestaurante);
        return Prato.list("restaurante", restauranteOp.get());
    }

    @POST
    @Transactional
    public Response adicionar(@PathParam("idRestaurante") Long idRestaurante, Prato dto) {
        Optional<Restaurante> restauranteOp = buscarValidarRestaurante(idRestaurante);

        Prato prato = new Prato();
        prato.nome = dto.nome;
        prato.descricao = dto.descricao;
        prato.preco = dto.preco;
        prato.restaurante = restauranteOp.get();
        prato.persist();

        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public void atualizar(@PathParam("idRestaurante") Long idRestaurante, @PathParam("id") Long id, Prato dto) {
        Optional<Restaurante> restauranteOp = buscarValidarRestaurante(idRestaurante);

        Optional<Prato> pratoOp = Prato.findByIdOptional(id);
        if (pratoOp.isEmpty()) {
            throw new NotFoundException("Prato não existe");
        }

        Prato prato = pratoOp.get();
        prato.preco = dto.preco;
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
