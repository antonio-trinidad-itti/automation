package TestBack.BackendCore;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class GestionRazonSocialFlujoCompleto {

    @Test
    public void flujoCompletoGestionRazonSocial() {

        baseURI = "https://backend-ms-core-development-yacwu.humanitti-sdlc.itti-platform.digital/core";

        //valores aleatorios
        String randomName = "Antonio Test " + UUID.randomUUID().toString().substring(0, 5);
        String randomEmail = "antonio." + UUID.randomUUID().toString().substring(0, 5) + "@gmail.com";
        String randomRuc = String.valueOf(10000000 + new Random().nextInt(90000000));
        String randomPatronal = String.valueOf(100 + new Random().nextInt(900));

        System.out.println("===== INICIANDO CREACIÓN DE RAZÓN SOCIAL =====");
        System.out.println("Nombre generado: " + randomName);
        System.out.println("Email generado: " + randomEmail);
        System.out.println("RUC generado: " + randomRuc);
        System.out.println("Patronal generado: " + randomPatronal);

        Map<String, Object> createMap = new HashMap<>();
        createMap.put("name", randomName);
        createMap.put("ruc", randomRuc);
        createMap.put("ownerEmail", randomEmail);
        createMap.put("companyType", "unipersonal");
        createMap.put("employeeRange", "51-100");
        createMap.put("country", "Argentina");
        createMap.put("city", "Buenos Aires");
        createMap.put("address", "Av. Corrientes 1234");
        createMap.put("status", "ACTIVE");
        createMap.put("patronalNumbers", Collections.singletonList(randomPatronal));
        createMap.put("companyParentId", "3b2d624e-b505-469d-9fb5-925097d8847a");

        Response createResponse = given()
                .contentType(ContentType.JSON)
                .body(createMap)
                .when()
                .post("/v1/subsidiary")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        String idRazonSocial = createResponse.jsonPath().getString("id");
        System.out.println("ID de la razón social creada: " + idRazonSocial);

        System.out.println("===== CONSULTANDO DETALLE DE RAZÓN SOCIAL =====");

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/v1/subsidiary/" + idRazonSocial)
                .then()
                .log().all()
                .statusCode(200);

        System.out.println("===== ACTUALIZACIÓN PARCIAL DE RAZÓN SOCIAL =====");

        Map<String, Object> partialUpdateMap = new HashMap<>();
        partialUpdateMap.put("address", "Av. Corrientes 5678");
        partialUpdateMap.put("status", "INACTIVE");

        given()
                .contentType(ContentType.JSON)
                .body(partialUpdateMap)
                .when()
                .patch("/v1/subsidiary/" + idRazonSocial)
                .then()
                .log().all()
                .statusCode(200);

        System.out.println("===== ACTUALIZACIÓN COMPLETA DE RAZÓN SOCIAL =====");
        randomPatronal = String.valueOf(1 + new Random().nextInt(100));
        System.out.println("Patronal generado: " + randomPatronal);
        Map<String, Object> fullUpdateMap = new HashMap<>();
        fullUpdateMap.put("name", randomName + " Actualizado");
        fullUpdateMap.put("ruc", randomRuc);
        fullUpdateMap.put("ownerEmail", randomEmail);
        fullUpdateMap.put("companyType", "sociedad anónima");
        fullUpdateMap.put("employeeRange", "101-200");
        fullUpdateMap.put("country", "Argentina");
        fullUpdateMap.put("city", "Buenos Aires");
        fullUpdateMap.put("address", "Av. 9 de Julio 1000");
        fullUpdateMap.put("status", "ACTIVE");
        fullUpdateMap.put("patronalNumbers", Collections.singletonList(randomPatronal));
        fullUpdateMap.put("companyParentId", "3b2d624e-b505-469d-9fb5-925097d8847a");

        given()
                .contentType(ContentType.JSON)
                .body(fullUpdateMap)
                .when()
                .put("/v1/subsidiary/" + idRazonSocial)
                .then()
                .log().all()
                .statusCode(200);

        System.out.println("===== ELIMINANDO RAZÓN SOCIAL =====");

        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/v1/subsidiary/" + idRazonSocial)
                .then()
                .log().all()
                .statusCode(204);

        System.out.println("===== PROCESO COMPLETO FINALIZADO =====");
    }
}
