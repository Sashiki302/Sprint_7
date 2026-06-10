package data;

import com.github.javafaker.Faker;
import model.CourierModel;

public class CourierData {
    public static final String BASE_URI = "https://qa-scooter.praktikum-services.ru/";
    public static final String CREATE_COURIER_PATH = "/api/v1/courier";
    public static final String LOGIN_COURIER_PATH = "/api/v1/courier/login";
    public static final String DELETE_COURIER_PATH = "/api/v1/courier";

    public static CourierModel getRandomCourier() {
        Faker user = new Faker();
        return new CourierModel(user.name().lastName() + user.regexify("(0-9){4}"),user.regexify("(0-9){4}"),user.name().firstName());
    }
}
