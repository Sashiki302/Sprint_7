import io.restassured.response.Response;
import org.junit.Test;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.CoreMatchers.*;
import static steps.OrderSteps.*;

public class GetOrderTest extends BaseApiTest{
    @Test
    public void testGetOrderList() {
        Response response = getOrder();
        response.then()
                .log().all()
                .statusCode(HTTP_OK)
                .body("orders", notNullValue());
    }
}
