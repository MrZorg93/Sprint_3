import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestLoginCourier{

    private Integer courierId;

    @BeforeAll
    public void setUp(){
        new CourierMethods();
    }

    @AfterEach
    public void tearDown(){
        if(courierId  != null) {
            boolean isCourierDeleted = CourierMethods.deleteCourier(courierId);
            System.out.println("Test data cleared: " + isCourierDeleted);
            courierId = null;
        }
        else{
            System.out.println("It is nothing to clear =)");
        }
    }

    @DisplayName("Check courier can login with all fields")
    @Description("Checking status code 200 and field ID not null")
    @Test
    public void checkCourierCanLoginWithValidData() {
        CourierJson courier = CourierJson.randomCourier(10, 10, 10);
        boolean isCourierCreated = CourierMethods.createCourier(courier);

        courierId = CourierMethods.loginCourier(CourierAuthDataJson.from(courier));

        assertThat("Courier has not id", courierId, is(not(0)));

    }

    @Test
    @DisplayName("Check courier can not login without login")
    @Description("Checking status code 400 and error message")
    public void checkCourierCanNotLoginWithoutLogin() {
        CourierJson courier = CourierJson.randomCourier(10, 10, 10);
        boolean isCourierCreated = CourierMethods.createCourier(courier);

        String errorMessage = CourierMethods.tryToLoginCourierWithoutOneOfBodyField(new CourierAuthDataJson("",courier.getPassword()));

        assertThat("Courier login without login enter!", errorMessage, is("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Check courier can not login without password")
    @Description("Checking status code 400 and error message")
    public void checkCourierCanNotLoginWithoutPassword() {
        CourierJson courier = CourierJson.randomCourier(10, 10, 10);
        boolean isCourierCreated = CourierMethods.createCourier(courier);

        String errorMessage = CourierMethods.tryToLoginCourierWithoutOneOfBodyField(new CourierAuthDataJson(courier.getLogin(), ""));

        assertThat("Courier login without password enter!", errorMessage, is("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Check courier can not login with invalid login-password pair")
    @Description("Checking status code 404 and error message")
    public void checkCourierCanNotLoginWithInvalidData() {
        CourierJson courier1 = CourierJson.randomCourier(10, 10, 10);
        boolean isCourierCreated1 = CourierMethods.createCourier(courier1);
        CourierJson courier2 = CourierJson.randomCourier(10, 10, 10);
        boolean isCourierCreated2 = CourierMethods.createCourier(courier2);

        String errorMessage = CourierMethods.tryToLoginCourierWithInvalidLoginPasswordPair(new CourierAuthDataJson(courier1.getLogin(), courier2.getPassword()));

        assertThat("Courier login with wrong login:password pair!", errorMessage, is("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Check courier can not login with invalid login")
    @Description("Checking status code 404 and error message")
    public void checkCourierCanNotLoginWithInvalidLogin() {
        CourierJson courier = CourierJson.randomCourier(10, 10, 10);
        boolean isCourierCreated = CourierMethods.createCourier(courier);
        String courierLogin = RandomStringUtils.randomAlphabetic(10);

        String errorMessage = CourierMethods.tryToLoginCourierWithInvalidLoginPasswordPair(new CourierAuthDataJson(courierLogin, courier.getPassword()));

        assertThat("Courier login with wrong login!", errorMessage, is("Учетная запись не найдена"));
    }
}
