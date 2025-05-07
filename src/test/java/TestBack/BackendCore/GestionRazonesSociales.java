package TestBack.BackendCore;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class GestionRazonesSociales {

        @Test
        public void testCrearNuevaRazonSocial (){
            baseURI = "https://backend-ms-core-development-yacwu.humanitti-sdlc.itti-platform.digital/core";

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", "Antonio");
            map.put("ruc", "498713213123");
            map.put("ownerEmail", "antonio.ittiprueba12@gmail");
            map.put("companyType", "unipersonal");
            map.put("employeeRange", "51-100");
            map.put("country", "Argentina");
            map.put("city", "Buenos Aires");
            map.put("address", "string");
            map.put("status", "string");
            map.put("patronalNumbers", Collections.singletonList("919"));
            map.put("companyParentId", "3b2d624e-b505-469d-9fb5-925097d8847a");

            given()
                    .contentType(ContentType.JSON)
                    .body(map)
                    .when()
                    .post("/v1/subsidiary")
                    .then()
                    .log().all()
                    .statusCode(200);


        }
        @Test
        public void testObtenerDetalleRazonSocial (){
            baseURI = "https://backend-ms-core-development-yacwu.humanitti-sdlc.itti-platform.digital/core";

            given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get("/v1/subsidiary/3b2d624e-b505-469d-9fb5-925097d8847a")
                    .then()
                    .log().all()
                    .statusCode(200);
        }
        @Test
        public void testObtenerListadoRazonesSociales (){
            baseURI = "https://backend-ms-core-development-yacwu.humanitti-sdlc.itti-platform.digital/core";

            given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get("/v1/subsidiary?parentCompanyId=3b2d624e-b505-469d-9fb5-925097d8847a&page=0&size=10&sortBy=id")
                    .then()
                    .log().all()
                    .statusCode(200);
        }
        @Test
        public void testActualizacionParcialRazonSocial (){
        baseURI = "https://backend-ms-core-development-yacwu.humanitti-sdlc.itti-platform.digital/core";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "Antonio");
        map.put("ruc", "498713213");
        map.put("ownerEmail", "antonio.ittiprueba1@gmail");
        map.put("companyType", "unipersonal");
        map.put("employeeRange", "51-100");
        map.put("country", "Argentina");
        map.put("city", "Buenos Aires");
        map.put("address", "Av. Corrientes 1234");
        map.put("status", "ACTIVE");
        map.put("license", "LIC-12345");
        map.put("companyParentId", "3b2d624e-b505-469d-9fb5-925097d8847a");

        given()
                .contentType(ContentType.JSON)
                .body(map)
                .when()
                .patch("/v1/subsidiary/89467457-3230-4c14-a369-56d5a831c130")
                .then()
                .log().all()
                .statusCode(200);


    }
        @Test
        public void testActualizacionCompletaRazonSocial (){
        baseURI = "https://backend-ms-core-development-yacwu.humanitti-sdlc.itti-platform.digital/core";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "Antonio S.A.");
        map.put("ruc", "498713213");
        map.put("ownerEmail", "antonio.ittiprueba1@gmail.com");
        map.put("companyType", "unipersonal");
        map.put("employeeRange", "51-100");
        map.put("country", "Argentina");
        map.put("city", "Buenos Aires");
        map.put("address", "Av. Corrientes 1234");
        map.put("status", "ACTIVE");
        map.put("patronalNumbers", Collections.singletonList("1199"));
        map.put("companyParentId", "3b2d624e-b505-469d-9fb5-925097d8847a");

        given()
                .contentType(ContentType.JSON)
                .body(map)
                .when()
                .put("/v1/subsidiary/89467457-3230-4c14-a369-56d5a831c130")
                .then()
                .log().all()
                .statusCode(200);
    }
        @Test
        public void testEliminarRazonSocial () {
        baseURI = "https://backend-ms-core-development-yacwu.humanitti-sdlc.itti-platform.digital/core";

        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/v1/subsidiary/db88e1bf-df21-4140-9d10-204557d36cfb")
                .then()
                .log().all()
                .statusCode(204);
    }


}

