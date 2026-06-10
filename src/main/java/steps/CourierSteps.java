package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.CourierModel;

import static data.CourierData.*;
import static io.restassured.RestAssured.given;

public class CourierSteps {

    @Step ("Создание курьера")
    public static Response createCourier (CourierModel courier){
        return
                given()
                        .log().all()
                .contentType(ContentType.JSON)
                        .body(courier)
                .when()
                        .post(CREATE_COURIER_PATH);
    }
    @Step ("Логин курьера")
    public static Response loginCourier (CourierModel courier){
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(courier)
                .when()
                    .post(LOGIN_COURIER_PATH);
    }
    @Step ("Удаление курьера")
    public static void deleteCourier (int courierId){
                given()
                .log().all()
                .delete(DELETE_COURIER_PATH + courierId);
    }
}
