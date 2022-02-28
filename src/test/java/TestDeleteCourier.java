import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestDeleteCourier{

    @BeforeAll
    public void setUp(){
        new CourierMethods();
    }

    @Test
    @DisplayName("Delete courier with correct request")
    public void deleteCourierWithCorrectRequest(){
        CourierJson courier = CourierJson.randomCourier(10 , 10, 10);
        boolean isCourierCreated = CourierMethods.createCourier(courier);
        Integer courierId = CourierMethods.loginCourier(CourierAuthDataJson.from(courier));
        boolean isCourierDeleted = CourierMethods.deleteCourier(courierId);

        assertTrue(isCourierDeleted, "Deletion failed!");
    }


}
