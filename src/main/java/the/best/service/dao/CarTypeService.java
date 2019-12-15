package the.best.service.dao;

import the.best.entity.CarType;

import java.util.List;

public interface CarTypeService {
    List<CarType> getAll();

    boolean remove(CarType carType);
}
