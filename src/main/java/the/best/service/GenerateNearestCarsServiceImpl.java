package the.best.service;

import lombok.extern.slf4j.Slf4j;
import the.best.dao.CarDAO;
import the.best.dao.CarTypeDAO;
import the.best.entity.*;
import the.best.web.data.Order;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GenerateNearestCarsServiceImpl implements GenerateNearestCarsService {

    CarDAO carDAO = new CarDAO();
    CarTypeDAO carTypeDAO = new CarTypeDAO();
    NearestCarService nearestCarService = new NearestCarServiceImpl();
    TimeCalculationService timeCalculationService = new TimeCalculationServiceImpl();

    @Override
    public List<Order> generate(Location origin, Location destination) {

        List<Order> res = new ArrayList<>();

        List<CarType> carTypes = carTypeDAO.getAll();

        long timeToReachFromOriginToDestination = timeCalculationService.calculateTime(origin, destination);
        log.info("timeToReachFromOriginToDestination=" + timeToReachFromOriginToDestination);
        for(CarType carType : carTypes){
            List<Order> carsWithLocationIdByCarType = carDAO.getAllWithLocationIdActiveByTypeId(carType.getId());
            OrderAndDeltaTime orderAndDeltaTime = nearestCarService.findNearest(origin, carsWithLocationIdByCarType);
            if(orderAndDeltaTime != null) {
                Order order = orderAndDeltaTime.getOrder();


                order.setTimeToReachOrigin(orderAndDeltaTime.getDelta());
                order.setCarType(carType);

                order.setTimeToReachDestination((int) (timeToReachFromOriginToDestination + orderAndDeltaTime.getDelta()));

                res.add(order);
            }
        }

        return res;
    }
}
