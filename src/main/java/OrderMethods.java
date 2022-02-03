import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.util.ArrayList;

public class OrderMethods {
    public static OrderCardJson createOrderDataWithBoothColours(){
        String firstName = RandomStringUtils.randomAlphabetic(7);
        String lastName = RandomStringUtils.randomAlphabetic(7);
        String address = RandomStringUtils.randomAlphabetic(30);
        String metroStation = RandomStringUtils.randomAlphabetic(13);
        String phone = RandomStringUtils.randomNumeric(11);
        Integer rentTime = (int)(Math.random()*((300)+1));
        String deliveryDate = LocalDate.now().toString();
        String comment = RandomStringUtils.randomAlphabetic(50);
        ArrayList<String> colour = new ArrayList<>();
        colour.add("BLACK");
        colour.add("GREY");
        return new OrderCardJson(firstName,
                lastName,
                address,
                metroStation,
                phone,
                rentTime,
                deliveryDate,
                comment,
                colour);

    }

    public static OrderCardJson createOrderDataWithBlackColour(){
        String firstName = RandomStringUtils.randomAlphabetic(7);
        String lastName = RandomStringUtils.randomAlphabetic(7);
        String address = RandomStringUtils.randomAlphabetic(30);
        String metroStation = RandomStringUtils.randomAlphabetic(13);
        String phone = RandomStringUtils.randomNumeric(11);
        Integer rentTime = (int)(Math.random()*((300)+1));
        String deliveryDate = LocalDate.now().toString();
        String comment = RandomStringUtils.randomAlphabetic(50);
        ArrayList<String> colour = new ArrayList<>();
        colour.add("BLACK");
        return new OrderCardJson(firstName,
                lastName,
                address,
                metroStation,
                phone,
                rentTime,
                deliveryDate,
                comment,
                colour);

    }

    public static OrderCardJson createOrderDataWithGreyColour(){
        String firstName = RandomStringUtils.randomAlphabetic(7);
        String lastName = RandomStringUtils.randomAlphabetic(7);
        String address = RandomStringUtils.randomAlphabetic(30);
        String metroStation = RandomStringUtils.randomAlphabetic(13);
        String phone = RandomStringUtils.randomNumeric(11);
        Integer rentTime = (int)(Math.random()*((300)+1));
        String deliveryDate = LocalDate.now().toString();
        String comment = RandomStringUtils.randomAlphabetic(50);
        ArrayList<String> colour = new ArrayList<>();
        colour.add("GREY");
        return new OrderCardJson(firstName,
                lastName,
                address,
                metroStation,
                phone,
                rentTime,
                deliveryDate,
                comment,
                colour);
    }

    public static OrderCardJson createOrderDataWithoutColours(){
        String firstName = RandomStringUtils.randomAlphabetic(7);
        String lastName = RandomStringUtils.randomAlphabetic(7);
        String address = RandomStringUtils.randomAlphabetic(30);
        String metroStation = RandomStringUtils.randomAlphabetic(13);
        String phone = RandomStringUtils.randomNumeric(11);
        Integer rentTime = (int)(Math.random()*((300)+1));
        String deliveryDate = LocalDate.now().toString();
        String comment = RandomStringUtils.randomAlphabetic(50);
        ArrayList<String> colour = new ArrayList<>();
        return new OrderCardJson(firstName,
                lastName,
                address,
                metroStation,
                phone,
                rentTime,
                deliveryDate,
                comment);
    }

    @Step("Send order data {orderCardJson} to creation order")
    public static Integer orderCreationRequest(OrderCardJson orderCardJson){
        return ApiSpecification.setUp()
                .when()
                .body(orderCardJson)
                .when()
                .post("orders")
                .then()
                .assertThat()
                .statusCode(201)
                .extract()
                .path("track");
    }
}
