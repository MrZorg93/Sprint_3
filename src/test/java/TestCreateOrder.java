import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

public class TestCreateOrder {
    private OrderCardJson orderData;
    private Integer trackId;

    @Test
    @DisplayName("Проверка создания заказа без выбора цвета")
    public void checkOrderCreationWithoutColour(){
        orderData = OrderMethods.createOrderDataWithoutColours();
        trackId = OrderMethods.orderCreationRequest(orderData);
        assertThat("Не удалось создать заказ!", trackId, is(not(0)));
    }

    @Test
    @DisplayName("Проверка создания заказа с выбором цвета BLACK")
    public void checkOrderCreationWithBlackColour(){
        orderData = OrderMethods.createOrderDataWithBlackColour();
        trackId = OrderMethods.orderCreationRequest(orderData);
        assertThat("Не удалось создать заказ!", trackId, is(not(0)));
    }

    @Test
    @DisplayName("Проверка создания заказа с выбором цвета GREY")
    public void checkOrderCreationWithGreyColour(){
        orderData = OrderMethods.createOrderDataWithGreyColour();
        trackId = OrderMethods.orderCreationRequest(orderData);
        assertThat("Не удалось создать заказ!", trackId, is(not(0)));
    }

    @Test
    @DisplayName("Проверка создания заказа с выбором двух цветов")
    public void checkOrderCreationWithTwoColours(){
        orderData = OrderMethods.createOrderDataWithBoothColours();
        trackId = OrderMethods.orderCreationRequest(orderData);
        assertThat("Не удалось создать заказ!", trackId, is(not(0)));
    }
}
