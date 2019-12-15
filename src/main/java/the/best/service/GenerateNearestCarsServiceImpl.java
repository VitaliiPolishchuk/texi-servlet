package the.best.service;

import lombok.extern.slf4j.Slf4j;
import the.best.dao.CarDAO;
import the.best.dao.CarTypeDAO;
import the.best.entity.*;
import the.best.service.dao.CarService;
import the.best.service.dao.CarServiceImpl;
import the.best.service.dao.CarTypeService;
import the.best.service.dao.CarTypeServiceImpl;
import the.best.web.data.Order;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GenerateNearestCarsServiceImpl implements GenerateNearestCarsService {

    CarTypeService carTypeService = new CarTypeServiceImpl();
    NearestCarService nearestCarService = new NearestCarServiceImpl();
    TimeCalculationService timeCalculationService = new TimeCalculationServiceImpl();
    CarService carService = new CarServiceImpl();

    @Override
    public List<Order> generate(Location origin, Location destination) {
        log.info(String.format("Generating nearest car with origin = %s and destination = %s", origin, destination));
        List<Order> res = new ArrayList<>();

        List<CarType> carTypes = carTypeService.getAll();

        long timeToReachFromOriginToDestination = timeCalculationService.calculateTime(origin, destination);
        log.info("timeToReachFromOriginToDestination=" + timeToReachFromOriginToDestination);
        for (CarType carType : carTypes) {
            List<Order> carsWithLocationIdByCarType = carService.convert(carService.getAllWithLocationIdActiveByTypeId(carType.getId()));
            OrderAndDeltaTime orderAndDeltaTime = nearestCarService.findNearest(origin, carsWithLocationIdByCarType);
            if (orderAndDeltaTime != null) {
                Order order = orderAndDeltaTime.getOrder();
                order.setTimeToReachOrigin(orderAndDeltaTime.getDelta());
                order.setCarType(carType);
                order.setTimeToReachDestination((int) (timeToReachFromOriginToDestination + orderAndDeltaTime.getDelta()));
                res.add(order);
            }
        }
        log.info(String.format("Generated nearest car with origin = %s and destination = %s", origin, destination));
        return res;
    }
}
