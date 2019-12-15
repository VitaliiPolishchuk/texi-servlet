package the.best.service.dao;

import the.best.entity.Car;
import the.best.entity.CarType;
import the.best.web.data.CarForm;
import the.best.web.data.Order;

import java.util.List;
import java.util.Map;

public interface CarService {
    Map<Integer, List<Car>> getCars(Iterable<CarType> types);

    List<Car> getCars(int carTypeId);

    Car getById(int id);

    boolean validate(CarForm carForm);

    boolean intToBool(int bool);

    Car getCar(CarForm carForm);

    boolean save(Car car);

    boolean update(Car car);

    boolean remove(Car car);

    List<Order> convert(List<Car> cars);

    List<Car> getAllWithLocationIdActiveByTypeId(int carTypeId);
}
