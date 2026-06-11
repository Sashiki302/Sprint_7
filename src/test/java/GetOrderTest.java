import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.CoreMatchers.*;
import static steps.OrderSteps.*;

public class GetOrderTest extends BaseApiTest{
    @Test
    @DisplayName("Вызов листа заказов")
    @Description("Вызываем лист заказов и проверяем что он успешно отображается")
    public void testGetOrderList() {
        Response response = getOrder();
        response.then()
                .log().all()
                .statusCode(HTTP_OK)
                .body("orders", notNullValue());
    }
}
