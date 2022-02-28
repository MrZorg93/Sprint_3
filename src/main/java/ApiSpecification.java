import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiSpecification {
    public static RequestSpecification setUp() {
        return given()
                .baseUri("http://qa-scooter.praktikum-services.ru/api/v1/")
                .header("Content-type", "application/json")
                .auth().none();
    }
}
