import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestCreateCourier  {

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


    @DisplayName("Check courier creation with correct login")
    @Description("Checking status code 201 and not null id with different length of login")
    @ParameterizedTest(name = "#{index} - login length = {0}")
    @ValueSource(ints = {2, 3, 5, 10, 50})
    public void checkCourierCreationWithDifferentLoginLengths(Integer arg) {
        CourierJson courier = CourierJson.randomCourier(arg, 10, 10);
        boolean isCourierCreated = CourierMethods.createCourier(courier);

        courierId = CourierMethods.loginCourier(CourierAuthDataJson.from(courier));

        assertTrue(isCourierCreated, "Courier is not created!");
        assertThat("Courier has not id", courierId, is(not(0)));

    }


    @DisplayName("Check courier creation with correct password")
    @Description("Checking status code 201 and not null id with different length of password")
    @ParameterizedTest(name = "#{index} - password length = {0}")
    @ValueSource(ints = {4, 5, 10, 50})
    public void checkCourierCreationWithDifferentPassword(Integer arg) {
        CourierJson courier = CourierJson.randomCourier(10, arg, 10);
        boolean isCourierCreated = CourierMethods.createCourier(courier);

        courierId = CourierMethods.loginCourier(CourierAuthDataJson.from(courier));

        assertTrue(isCourierCreated, "Courier is not created!");
        assertThat("Courier has not id", courierId, is(not(0)));

    }

    @DisplayName("Check courier creation with correct firstName")
    @Description("Checking status code 201 and not null id with different length of firstName")
    @ParameterizedTest(name = "#{index} - firstName length = {0}")
    @ValueSource(ints = {2, 3, 5, 10, 50})
    public void checkCourierCreationWithDifferentFirstName(Integer arg) {
        CourierJson courier = CourierJson.randomCourier(10, 10, arg);
        boolean isCourierCreated = CourierMethods.createCourier(courier);

        courierId = CourierMethods.loginCourier(CourierAuthDataJson.from(courier));

        assertTrue(isCourierCreated, "Courier is not created!");
        assertThat("Courier has not id", courierId, is(not(0)));

    }

    @Test
    @DisplayName("Check courier creation without login")
    @Description("Checking status code 400 and error message returned")
    public void checkCourierCreationWithoutLogin() {
        CourierJson courier = CourierJson.randomCourier(0, 10, 10);
        String errorMessage = CourierMethods.createCourierWithoutOneOfBodyField(courier);

        assertThat("Wrong courier creation!!!", errorMessage, is(not("")));
        assertThat("Message is wrong!", errorMessage, is("Недостаточно данных для создания учетной записи"));
    }

        @Test
    @DisplayName("Check courier creation without password") // имя теста
    @Description("Checking status code 400 and error message returned")
    public void checkCourierCreationWithoutPassword() {
            CourierJson courier = CourierJson.randomCourier(10, 0, 10);
            String errorMessage = CourierMethods.createCourierWithoutOneOfBodyField(courier);

            assertThat("Wrong courier creation!!!", errorMessage, is(not("")));
            assertThat("Message is wrong!", errorMessage, is("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Check courier creation without firstName") // имя теста
    @Description("Checking status code 400 returned")
    public void checkCourierCreationWithoutFirstName() {
        CourierJson courier = CourierJson.randomCourier(10, 0, 10);
        String errorMessage = CourierMethods.createCourierWithoutOneOfBodyField(courier);

        assertThat("Wrong courier creation!!!", errorMessage, is(not("")));
        assertThat("Message is wrong!", errorMessage, is("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Check courier creation impossible with occupated login")
    @Description("Checking status code 409 and error message returned")
    public void checkCourierCreationWithOccupatedLogin() {
        CourierJson courier1 = CourierJson.randomCourier(10, 10, 10);
        CourierJson courier2 = new CourierJson(courier1.getLogin(), RandomStringUtils.randomAlphanumeric(10), RandomStringUtils.randomAlphabetic(10));
        String errorMessage = CourierMethods.createCourierWithOccupatedLogin(courier2);

        assertThat("Now created courier with occupated login!!!", errorMessage, is(not("")));
        assertThat("Message is wrong!", errorMessage, is("Этот логин уже используется"));
    }

}
