import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.hamcrest.CoreMatchers.notNullValue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestOrderList {

    @BeforeAll
    public void setUp(){
        new CourierMethods();
    }

    @Test
    @DisplayName("Check order list is not null")
    public void checkOrderListResponseIsNotNull(){
        OrderListResponseJson orderListResponse = ApiSpecification.setUp()
                .when()
                .get("/api/v1/orders").as(OrderListResponseJson.class);

        MatcherAssert.assertThat(orderListResponse, notNullValue());
    }
}
