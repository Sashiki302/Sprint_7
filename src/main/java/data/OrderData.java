package data;

import com.github.javafaker.Faker;
import model.OrderModel;
import java.util.List;

public class OrderData {
    public static final String CREATE_ORDER_PATH = "/api/v1/orders";
    public static final String CANCEL_ORDER_PATH = "/api/v1/orders/cancel";
    public static final String GET_ORDER_PATH = "/api/v1/orders";


    public static OrderModel getRandomOrder(List<String> color) {
        Faker order = new Faker();
        return new OrderModel(
                order.name().firstName(),
                order.name().lastName(),
                order.address().streetAddress(),
                "4",
                order.phoneNumber().phoneNumber(),
               5,
                "2026-06-20",
                "Хочу кушать",
                color
        );
    }
}