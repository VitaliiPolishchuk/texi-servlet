package the.best.service.dao;

import the.best.dao.CarDAO;
import the.best.dao.CarTypeDAO;
import the.best.dao.EntityDao;
import the.best.entity.Car;
import the.best.entity.CarType;
import the.best.enums.DaoType;
import the.best.pattern.factory.DaoFactory;

import java.util.List;

public class CarTypeServiceImpl implements CarTypeService {

    EntityDao<CarType, Integer> carTypeDAO;
    CarDAO carDAO;

    public CarTypeServiceImpl() {
        carDAO = (CarDAO) DaoFactory.getEntityDao(DaoType.CAR);
        carTypeDAO = DaoFactory.getEntityDao(DaoType.CAR_TYPE);
    }

    @Override
    public List<CarType> getAll() {
        return carTypeDAO.getAll();
    }

    @Override
    public boolean remove(CarType carType) {
        List<Car> cars = carDAO.getAllByCarTypeId(carType.getId());
        for (Car car : cars) {
            carDAO.remove(car);
        }
        return carTypeDAO.remove(carType);
    }
}
