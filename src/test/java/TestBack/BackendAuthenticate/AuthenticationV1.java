package TestBack.BackendAuthenticate;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationV1 {

    private String sessionToken;
    private String refreshToken;

    public void login() {
        baseURI = "https://backend-authenticate-api-development-tydfy.humanitti-sdlc.itti-platform.digital/authenticate";

        Map<String, Object> credentials = new HashMap<>();
        credentials.put("username", "bismarck.berrios@itti.digital");
        credentials.put("password", "Admin123");
        credentials.put("tenant", "HUMANITTI");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(credentials)
                .when()
                .post("/v1/login")
                .then()
                //.log().all()
                .statusCode(200)
                .extract()
                .response();

        sessionToken = response.jsonPath().getString("sessionToken");
        refreshToken = response.jsonPath().getString("refreshToken");

        System.out.println("Session Token: " + sessionToken);
        System.out.println("Refresh Token: " + refreshToken);

        // Validaciones
        Assert.assertNotNull(sessionToken, "El sessionToken no debe ser null.");
        Assert.assertFalse(sessionToken.isEmpty(), "El sessionToken no debe estar vacío.");
        Assert.assertNotNull(refreshToken, "El refreshToken no debe ser null.");
        Assert.assertFalse(refreshToken.isEmpty(), "El refreshToken no debe estar vacío.");
    }

    @Test
    public void testLogin() {
        login();
    }

    @Test(dependsOnMethods = "testLogin")
    public void testRefreshToken() {
        baseURI = "https://backend-authenticate-api-development-tydfy.humanitti-sdlc.itti-platform.digital/authenticate";

        String oldSessionToken = sessionToken;

        Map<String, Object> body = new HashMap<>();
        body.put("sessionToken", sessionToken);
        body.put("refreshToken", refreshToken);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/v1/refreshToken")
                .then()
                //.log().all()
                .statusCode(200)
                .extract()
                .response();

        // Actualiza tokens
        sessionToken = response.jsonPath().getString("sessionToken");
        refreshToken = response.jsonPath().getString("refreshToken");

        System.out.println("Nuevo Session Token: " + sessionToken);
        System.out.println("Nuevo Refresh Token: " + refreshToken);

        // Validaciones
        Assert.assertNotNull(sessionToken, "El nuevo sessionToken no debe ser null.");
        Assert.assertFalse(sessionToken.isEmpty(), "El nuevo sessionToken no debe estar vacío.");
        Assert.assertNotNull(refreshToken, "El nuevo refreshToken no debe ser null.");
        Assert.assertFalse(refreshToken.isEmpty(), "El nuevo refreshToken no debe estar vacío.");

        // Validación: el nuevo sessionToken debe ser diferente al anterior
        Assert.assertNotEquals(sessionToken, oldSessionToken, "El nuevo sessionToken debe ser diferente al anterior.");
    }

    @Test(dependsOnMethods = "testRefreshToken")
    public void testLogout() {
        baseURI = "https://backend-authenticate-api-development-tydfy.humanitti-sdlc.itti-platform.digital/authenticate";

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + sessionToken)
                .when()
                .post("/v1/logout")
                .then()
                .log().all()
                .statusCode(200);
    }
}
