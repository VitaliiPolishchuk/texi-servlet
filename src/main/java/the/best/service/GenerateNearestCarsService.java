package the.best.service;

import the.best.web.data.Order;
import the.best.entity.Location;

import java.util.List;

public interface GenerateNearestCarsService {
    List<Order> generate(Location origin, Location destination);
}
