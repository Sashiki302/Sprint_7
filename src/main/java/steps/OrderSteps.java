package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.OrderModel;

import static data.OrderData.*;
import static io.restassured.RestAssured.given;

public class OrderSteps {

    @Step("Создание заказа с цветом")
    public static Response createOrder(OrderModel order) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(order)
                .when()
                .post(CREATE_ORDER_PATH);
    }

    @Step("Отмена заказа по номеру трека")
    public static Response cancelOrder(int track) {
        return given()
                .log().all()
                .queryParam("track", track)
                .when()
                .put(CANCEL_ORDER_PATH);
    }
    @Step("Получение списка заказов")
    public static Response getOrder() {
        return given()
                .log().all()
                .when()
                .get(GET_ORDER_PATH);
    }
}