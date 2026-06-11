import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.OrderModel;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static data.OrderData.*;
import static java.net.HttpURLConnection.*;
import static org.hamcrest.CoreMatchers.*;
import static steps.OrderSteps.*;

@RunWith(Parameterized.class)
public class CreateOrderTest extends BaseApiTest {

    private final List<String> color;
    private int track;

    public CreateOrderTest(List<String> color) {
        this.color = color;
    }
    @Parameterized.Parameters()
    public static Object[][] getTestData() {
        return new Object[][] {
                {List.of("BLACK")},
                {List.of("GREY")},
                {List.of("BLACK", "GREY")},
                {List.of("")}
        };
    }
    @After
    public void cleanOrder() {
        if (track != 0) {
            cancelOrder(track);
        }
    }
    @Test
    @DisplayName("Создание заказа с разным цветом")
    @Description("Генерируем новый заказ с рандомным цветом и проверяем что он верно создан")
    public void testCreateOrderDifferentColor() {
        OrderModel order = getRandomOrder(color);
        Response response = createOrder(order);
        track = response.path("track");
        response.then()
                .log().all()
                .statusCode(HTTP_CREATED)
                .body("track", notNullValue());
    }
}