package the.best.service;

import the.best.entity.Discount;
import the.best.web.data.AjaxGetCarsResponse;
import the.best.web.data.LatLong;
import the.best.web.data.Order;
import the.best.entity.User;
import the.best.pattern.PriceCalculationChainBuilder;
import the.best.pattern.PriceCalculationChainBuilderImpl;
import the.best.pattern.PriceCalculationService;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    PriceCalculationChainBuilder priceCalculationChainBuilder = new PriceCalculationChainBuilderImpl();
    GetPlacePositionService getPlacePositionService = new GetPlacePositionServiceImpl();

    @Override
    public void calculatePrice(List<Order> orders, User user, Discount discount) {
        PriceCalculationService priceCalculationService = priceCalculationChainBuilder.buildStandart();
        for(Order order : orders){
            priceCalculationService.calculate(order, user, discount);
        }
    }

    @Override
    public Order getCarById(List<Order> orders, int car_id) {
        Order desirableOrder = null;
        for(Order order : orders){
            if(order.getCar().getId() == car_id){
                desirableOrder = order;
            }
        }
        return desirableOrder;
    }

    private AjaxGetCarsResponse convertAjaxGetCarResponse(Order order){
        AjaxGetCarsResponse ajaxGetCarsResponse = new AjaxGetCarsResponse();
        ajaxGetCarsResponse.setCar(order.getCar());
        LatLong latLong = getPlacePositionService.getPosition(order.getCarLocation().getLocationName());
        ajaxGetCarsResponse.setLatLong(latLong);
        return ajaxGetCarsResponse;
    }

    @Override
    public List<AjaxGetCarsResponse> convertAjaxGetCarResponse(List<Order> orders){
        List<AjaxGetCarsResponse> res = new ArrayList<>();
        for(Order order : orders){
            res.add(convertAjaxGetCarResponse(order));
        }
        return res;
    }

}
