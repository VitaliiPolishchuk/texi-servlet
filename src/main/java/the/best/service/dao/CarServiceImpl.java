package the.best.service.dao;

import the.best.dao.CarDAO;
import the.best.dao.CarTypeDAO;
import the.best.dao.EntityDao;
import the.best.entity.Car;
import the.best.entity.CarType;
import the.best.entity.Location;
import the.best.web.data.builder.OrderBuilder;
import the.best.enums.DaoType;
import the.best.pattern.factory.DaoFactory;
import the.best.web.data.CarForm;
import the.best.web.data.Order;

import java.util.*;

public class CarServiceImpl implements CarService {

    CarDAO carDAO;
    EntityDao<CarType, Integer> carTypeDAO;

    public CarServiceImpl() {
        carDAO = (CarDAO) DaoFactory.getEntityDao(DaoType.CAR);
        carTypeDAO = DaoFactory.getEntityDao(DaoType.CAR);
    }

    public void setCarDAO(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public void setCarTypeDAO(CarTypeDAO carTypeDAO) {
        this.carTypeDAO = carTypeDAO;
    }

    @Override
    public List<Car> getCars(int carTypeId) {
        return carDAO.getAllByCarTypeId(carTypeId);
    }

    @Override
    public Map<Integer, List<Car>> getCars(Iterable<CarType> types) {

        Map<Integer, List<Car>> res = new HashMap<>();

        Iterator<CarType> iterator = types.iterator();
        while (iterator.hasNext()) {
            CarType type = iterator.next();
            List<Car> cars = getCars(type.getId());
            res.put(type.getId(), cars);
        }

        return res;
    }

    public Car getById(int id) {
        return carDAO.getById(id);
    }

    @Override
    public boolean validate(CarForm carForm) {
        if (carForm == null) {
            return false;
        }

        if (carForm.getName().isEmpty()) {
            return false;
        }

        if (carTypeDAO.getById(carForm.getCarType()) == null) {
            return false;
        }
        if (carForm.getLocationId().isEmpty()) {
            return false;
        }

        return true;
    }

    public boolean intToBool(int bool) {
        return bool == 0 ? false : true;
    }

    public Car getCar(CarForm carForm) {
        Car car = new Car();
        car.setCarName(carForm.getName());
        car.setIsActive(carForm.isAvailable());
        car.setCarLocationId(carForm.getLocationId());
        car.setPhotoUrl(carForm.getPhotoUrl());
        car.setCarTypeId(carForm.getCarType());
        if (carForm.getId() != 0) {
            car.setId(carForm.getId());
        }
        return car;

    }

    public boolean save(Car car) {
        return carDAO.create(car);
    }

    public boolean update(Car car) {
        return carDAO.update(car);
    }

    @Override
    public boolean remove(Car car) {
        return carDAO.remove(car);
    }


    @Override
    public List<Order> convert(List<Car> cars) {
        List<Order> res = new ArrayList<>();
        for (Car car : cars) {
            Location location = new Location(car.getCarLocationId());
            Order order = new Order(new OrderBuilder().setCar(car).setCarLocation(location));
            res.add(order);
        }
        return res;
    }

    @Override
    public List<Car> getAllWithLocationIdActiveByTypeId(int carTypeId) {
        return carDAO.getAllWithLocationIdActiveByTypeId(carTypeId);
    }
}
