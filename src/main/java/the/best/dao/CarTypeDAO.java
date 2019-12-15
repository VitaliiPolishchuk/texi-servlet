package the.best.dao;

import lombok.extern.slf4j.Slf4j;
import the.best.entity.Car;
import the.best.persistence.DataSourceFactory;
import the.best.entity.CarType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CarTypeDAO extends AbstractDao<CarType, Integer> {

    public static final String TABLE = "car_type";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TYPE_NAME = "type_name";
    public static final String COLUMN_PRICE_BOOKING = "price_booking";
    public static final String COLUMN_PRICE_PER_KM = "price_per_km";
    public static final String COLUMN_CAPACITY = "capacity";

    private static final String QUERY_CARS_TYPE = "SELECT * FROM " + TABLE;
    private static final String QUERY_BY_ID = "SELECT * FROM " + TABLE + " WHERE " + COLUMN_ID + " = ? ";

    private static final String DELETE_CAR_TYPE = "DELETE FROM " + TABLE + " WHERE " + COLUMN_ID + " = ?";

    private CarDAO carDAO = new CarDAO();

    public List<CarType> getAll() {
        return getAll(QUERY_CARS_TYPE, getMapper());
    }

    @Override
    public boolean create(CarType entity) {
        return false;
    }

    @Override
    public boolean update(CarType entity) {
        return false;
    }

    @Override
    public boolean remove(CarType entity) {
        return createUpdate(DELETE_CAR_TYPE, ps -> ps.setInt(1, entity.getId()));
    }

    public CarType getById(int id) {
        return getById(QUERY_BY_ID, ps -> ps.setInt(1, id), getMapper());
    }

    private EntityMapper<CarType> getMapper() {
        return resultSet -> new CarType(resultSet.getInt(COLUMN_ID), resultSet.getString(COLUMN_TYPE_NAME),
                resultSet.getDouble(COLUMN_PRICE_BOOKING), resultSet.getDouble(COLUMN_PRICE_PER_KM), resultSet.getInt(COLUMN_CAPACITY));
    }
}
