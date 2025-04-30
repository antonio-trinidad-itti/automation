package TestBack;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class Autenticacionv1 {
    @Test
    public void testLogin (){
        baseURI = "https://backend-authenticate-api-development-tydfy.humanitti-sdlc.itti-platform.digital/authenticate";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username","bismarck.berrios@itti.digital");
        map.put("password","Admin123");

    }
    @Test
    public void testLogout (){
        baseURI = "https://backend-authenticate-api-development-tydfy.humanitti-sdlc.itti-platform.digital/authenticate";

        String body = given()
                .contentType(ContentType.JSON)
                .when()
                .post("/v1/logout")
                .then()
                .extract()
                .body()
                .asString();
        System.out.println(body);
    }
    @Test
    public void testRefreshToken (){
        baseURI = "https://backend-authenticate-api-development-tydfy.humanitti-sdlc.itti-platform.digital/authenticate";

        String body = given()
                .contentType(ContentType.JSON)
                .when()
                .post("/v1/refreshToken")
                .then()
                .extract()
                .body()
                .asString();
        System.out.println(body);
    }
}
