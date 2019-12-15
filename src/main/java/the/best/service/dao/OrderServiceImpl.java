package the.best.service.dao;

import lombok.extern.slf4j.Slf4j;
import the.best.dao.OrderDAO;
import the.best.entity.Discount;
import the.best.service.GetPlacePositionService;
import the.best.service.GetPlacePositionServiceImpl;
import the.best.web.data.AjaxGetCarsResponse;
import the.best.web.data.LatLong;
import the.best.web.data.Order;
import the.best.entity.User;
import the.best.pattern.wrapper.PriceCalculationChainBuilder;
import the.best.pattern.wrapper.PriceCalculationChainBuilderImpl;
import the.best.pattern.wrapper.PriceCalculationService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderServiceImpl implements OrderService {
    PriceCalculationChainBuilder priceCalculationChainBuilder = new PriceCalculationChainBuilderImpl();
    GetPlacePositionService getPlacePositionService = new GetPlacePositionServiceImpl();

    public void setGetPlacePositionService(GetPlacePositionService getPlacePositionService) {
        this.getPlacePositionService = getPlacePositionService;
    }

    @Override
    public void calculatePrice(List<Order> orders, User user, Discount discount) {
        log.info("Calculation price for orders with user " + user);
        PriceCalculationService priceCalculationService = priceCalculationChainBuilder.buildStandart();
        for (Order order : orders) {
            priceCalculationService.calculate(order, user, discount);
        }
        log.info("Calculated price for orders with user " + user);
    }

    @Override
    public Order getCarById(List<Order> orders, int carId) {
        log.info("Getting car from orders by car id " + carId);
        Order desirableOrder = null;
        for (Order order : orders) {
            if (order.getCar().getId() == carId) {
                desirableOrder = order;
            }
        }
        log.info("Getted car from orders by car id " + desirableOrder);
        return desirableOrder;
    }

    private AjaxGetCarsResponse convertAjaxGetCarResponse(Order order) {
        AjaxGetCarsResponse ajaxGetCarsResponse = new AjaxGetCarsResponse();
        ajaxGetCarsResponse.setCar(order.getCar());
        LatLong latLong = getPlacePositionService.getPosition(order.getCarLocation().getLocationName());
        ajaxGetCarsResponse.setLatLong(latLong);
        return ajaxGetCarsResponse;
    }

    @Override
    public List<AjaxGetCarsResponse> convertAjaxGetCarResponse(List<Order> orders) {
        if (orders == null) {
            return null;
        }
        List<AjaxGetCarsResponse> res = new ArrayList<>();
        for (Order order : orders) {
            res.add(convertAjaxGetCarResponse(order));
        }
        return res;
    }

    @Override
    public boolean validate(Order order) {
        return true;
    }


}
