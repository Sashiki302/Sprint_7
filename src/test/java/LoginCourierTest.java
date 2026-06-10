import io.restassured.response.Response;
import model.CourierModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static data.CourierData.getRandomCourier;
import static java.net.HttpURLConnection.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static steps.CourierSteps.*;

public class LoginCourierTest extends BaseApiTest {
    private CourierModel courier;

    @Before
    public void setCourier() {
        courier = getRandomCourier();
        createCourier(courier);
    }

    @After
    public void cleanCourier() {
        courier.setFirstName(null);
        Response loginResponse = loginCourier(courier);
        if (loginResponse.statusCode() == HTTP_OK) {
            int courierId = loginResponse.path("id");
            deleteCourier(courierId);
        }
    }

    @Test
    public void testLoginCourierSuccess() {
        CourierModel loginData = new CourierModel(courier.getLogin(), courier.getPassword(), null);
        loginCourier(loginData)
                .then()
                .log().all()
                .statusCode(HTTP_OK)
                .body("id", notNullValue());
    }

    @Test
    public void testLoginIncorrectLoginFail() {
        CourierModel loginData = new CourierModel("NEW", courier.getPassword(), null);
        loginCourier(loginData)
                .then()
                .log().all()
                .statusCode(HTTP_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    public void testLoginIncorrectPasswordFail() {
        CourierModel loginData = new CourierModel(courier.getLogin(), "1234", null);
        loginCourier(loginData)
                .then()
                .log().all()
                .statusCode(HTTP_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    public void testLoginNull() {
        CourierModel loginData = new CourierModel(null, courier.getPassword(), null);
        loginCourier(loginData)
                .then()
                .log().all()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    public void testLoginPasswordNull() {
        CourierModel loginData = new CourierModel(courier.getLogin(), null, null);
        loginCourier(loginData)
                .then()
                .log().all()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для входа"));
    }
}