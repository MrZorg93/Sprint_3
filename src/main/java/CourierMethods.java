import io.qameta.allure.Step;

public class CourierMethods {

    @Step("Created {courier}")
    public static boolean createCourier(CourierJson courier){
        return ApiSpecification.setUp()
                .when()
                .body(courier)
                .when()
                .post("courier")
                .then()
                .assertThat()
                .statusCode(201)
                .extract()
                .path("ok");
    }

    @Step("Login with {courierAuthData}")
    public static Integer loginCourier(CourierAuthDataJson courierAuthData){
        return ApiSpecification.setUp()
                .when()
                .body(courierAuthData)
                .when()
                .post("courier/login")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("id");
    }

    @Step("Courier with id: {courierId} deletion")
    public static boolean deleteCourier(Integer courierId){
        return ApiSpecification.setUp()
                .when()
                .delete("courier/" + courierId)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("ok");
    }

    @Step("Trying to create courier with invalid body of request: {courier}")
    public static String createCourierWithoutOneOfBodyField(CourierJson courier){
        return ApiSpecification.setUp()
                .when()
                .body(courier)
                .when()
                .post("courier")
                .then()
                .assertThat()
                .statusCode(400)
                .extract()
                .path("message");
    }

    @Step("Try to create courier with occupated login")
    public static String createCourierWithOccupatedLogin(CourierJson courier){
        return ApiSpecification.setUp()
                .when()
                .body(courier)
                .when()
                .post("courier")
                .then()
                .assertThat()
                .statusCode(409)
                .extract()
                .path("message");
    }

    @Step("Try to login courier without one of request body fields")
    public static String tryToLoginCourierWithoutOneOfBodyField(CourierAuthDataJson courierAuthData){
        return ApiSpecification.setUp()
                .when()
                .body(courierAuthData)
                .when()
                .post("courier/login")
                .then()
                .assertThat()
                .statusCode(400)
                .extract()
                .path("message");

    }

    @Step("Try to login courier with invalid login : password pair")
    public static String tryToLoginCourierWithInvalidLoginPasswordPair(CourierAuthDataJson courierAuthData){
        return ApiSpecification.setUp()
                .when()
                .body(courierAuthData)
                .when()
                .post("courier/login")
                .then()
                .assertThat()
                .statusCode(404)
                .extract()
                .path("message");

    }

}
