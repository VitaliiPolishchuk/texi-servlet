package the.best.service;

import the.best.entity.Discount;
import the.best.web.data.AjaxGetCarsResponse;
import the.best.web.data.Order;
import the.best.entity.User;

import java.util.List;

public interface OrderService {
    void calculatePrice(List<Order> orders, User user, Discount discount);
    Order getCarById(List<Order> orders, int car_id);
    List<AjaxGetCarsResponse> convertAjaxGetCarResponse(List<Order> order);
}
