import io.restassured.response.Response;
import model.CourierModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static data.CourierData.*;
import static java.net.HttpURLConnection.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static steps.CourierSteps.*;

public class CreateCourierTest extends BaseApiTest {
        private CourierModel courier;
        @Before
        public void setCourier(){
            courier = getRandomCourier();
        }
        @After
        public void cleanCourier(){
            Response loginResponse = loginCourier(courier);
            if (loginResponse.getStatusCode() == HTTP_OK) {
                int courierId = loginResponse.path("id");
                deleteCourier(courierId);
            }
        }
        @Test
         public void testCreateCourierSuccess() {
        createCourier(courier)
                .then()
                .log().all()
                .statusCode(HTTP_CREATED)
                .body("ok", equalTo(true));
    }
    @Test
         public void testCreateCourierDuplicate() {
        createCourier(courier);
        createCourier(courier)
                    .then()
                    .log().all()
                    .statusCode(HTTP_CONFLICT)
                    .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }
    @Test
     public void testCreateCourierWithoutLogin() {
            courier.setLogin("");
        createCourier(courier)
                    .then()
                    .log().all()
                    .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
    @Test
     public void testCreateCourierWithoutPassword() {
            courier.setPassword("");
        createCourier(courier)
                    .then()
                    .log().all()
                    .statusCode(HTTP_BAD_REQUEST)
                    .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}
