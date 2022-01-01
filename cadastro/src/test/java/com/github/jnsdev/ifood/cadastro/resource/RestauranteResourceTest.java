package com.github.jnsdev.ifood.cadastro.resource;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.jnsdev.ifood.cadastro.CadastroTestLifecycleManager;
import com.github.jnsdev.ifood.cadastro.entity.Restaurante;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.approvaltests.Approvals;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

/**
 * @Autor Jairo Nascimento
 * @Created 27/11/2021 - 20:45
 */
@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTest
@QuarkusTestResource(CadastroTestLifecycleManager.class)
class RestauranteResourceTest {

    @Test
    @DataSet("restaurantes-cenario-1.yml")
    void testBuscarRestaurantes() {
        String resultado = given()
                .when()
                .get("/restaurantes")
                .then()
                .statusCode(200)
                .extract().asString();
        Approvals.verifyJson(resultado);
    }

    @Test
    @DataSet("restaurantes-cenario-1.yml")
    public void testAlterarRestaurante() {
        Restaurante dto = new Restaurante();
        dto.nome = "novoNome";
        Long parameterValue = 123L;

        given()
                .with().pathParam("id", parameterValue)
                .body(dto)
                .when()
                .put("/restaurantes/{id}")
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode())
                .extract().asString();

        Restaurante byId = Restaurante.findById(parameterValue);
        Assert.assertEquals(dto.nome, byId.nome);
    }

    private RequestSpecification given() {
        return RestAssured.given().contentType(ContentType.JSON);
    }
}