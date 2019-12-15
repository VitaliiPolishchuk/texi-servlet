package the.best.dao;

import lombok.extern.slf4j.Slf4j;
import the.best.entity.Car;
import the.best.persistence.DataSourceFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CarDAO extends AbstractDao<Car, Integer> {
    public static final String TABLE = "car";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CAR_NAME = "car_name";
    public static final String COLUMN_PHOTO_URL = "photo_url";
    public static final String COLUMN_CAR_TYPE_ID = "car_type_id";
    public static final String COLUMN_LOCATION_ID = "location_id";
    public static final String COLUMN_IS_ACTIVE = "is_active";

    private static final String QUERY_ALL_ACTIVE_CAR_BY_TYPE_ID = "SELECT * FROM " + TABLE + " WHERE " + COLUMN_CAR_TYPE_ID + " = ? AND " + COLUMN_IS_ACTIVE + " = 1";

    private static final String QUERY_ALL_CARS = "SELECT * FROM " + TABLE;
    private static final String DELETE_CAR = "DELETE FROM " + TABLE + " WHERE id = ?";
    private static final String QUERY_ALL_CAR_BY_TYPE_ID = "SELECT * FROM " + TABLE + " WHERE " + COLUMN_CAR_TYPE_ID + " = ?";
    private static final String UPDATE_CAR = "UPDATE " + TABLE + " SET " + COLUMN_CAR_NAME + " = ?, " + COLUMN_PHOTO_URL + " = ?," +
            COLUMN_CAR_TYPE_ID + " = ?, " + COLUMN_LOCATION_ID + " = ?, " + COLUMN_IS_ACTIVE + " = ?  WHERE " + COLUMN_ID + " = ?";
    private static final String QUERY_BY_ID = "SELECT * FROM " + TABLE + " WHERE " + COLUMN_ID + " = ? ";
    private static final String INSERT_CAR = "INSERT INTO " + TABLE + " (" +
            COLUMN_CAR_NAME + ", " +
            COLUMN_PHOTO_URL + ", " +
            COLUMN_CAR_TYPE_ID + ", " +
            COLUMN_LOCATION_ID + ", " +
            COLUMN_IS_ACTIVE + ") VALUES (?, ?, ?, ?, ?) ";


    private static final DataSourceFactory dataSourceFactory = DataSourceFactory.getInstance();

    @Override
    public Car getById(Integer id) {
        return getById(QUERY_BY_ID, ps -> ps.setInt(1, id), getMapper());
    }

    public List<Car> getAllWithLocationIdActiveByTypeId(int carTypeId) {
        return executeCustomQuering(QUERY_ALL_ACTIVE_CAR_BY_TYPE_ID,
                ps -> {
                    ps.setInt(1, carTypeId);
                },
                getMapper());
    }

    public List<Car> getAll() {
        return getAll(QUERY_ALL_CARS, getMapper());
    }

    @Override
    public boolean create(Car entity) {
        return createUpdate(INSERT_CAR, ps -> {
            ps.setString(1, entity.getCarName());
            ps.setString(2, entity.getPhotoUrl());
            ps.setInt(3, entity.getCarTypeId());
            ps.setString(4, entity.getCarLocationId());
            ps.setBoolean(5, entity.getIsActive());
        });
    }

    public List<Car> getAllByCarTypeId(int carTypeId) {
        return executeCustomQuering(QUERY_ALL_CAR_BY_TYPE_ID,
                ps -> {
                    ps.setInt(1, carTypeId);
                },
                getMapper());
    }

    private boolean toBoolean(int bool) {
        return bool == 1 ? true : false;
    }

    private int toInteger(boolean bool) {
        return bool ? 1 : 0;
    }

    public boolean update(Car car, Connection connection) throws SQLException {
        log.info("Updating car with id " + car.getId());
        PreparedStatement updateCarPreparedStatement = connection.prepareStatement(UPDATE_CAR);
        updateCarPreparedStatement.setString(1, car.getCarName());
        updateCarPreparedStatement.setString(2, car.getPhotoUrl());
        updateCarPreparedStatement.setInt(3, car.getCarTypeId());
        updateCarPreparedStatement.setString(4, car.getCarLocationId());
        updateCarPreparedStatement.setInt(5, toInteger(car.getIsActive()));
        updateCarPreparedStatement.setInt(6, car.getId());
        updateCarPreparedStatement.executeUpdate();
        log.info("Car was updated with id " + car.getId());
        return true;
    }

    public boolean update(Car entity) {
        return createUpdate(UPDATE_CAR, ps -> {
            ps.setString(1, entity.getCarName());
            ps.setString(2, entity.getPhotoUrl());
            ps.setInt(3, entity.getCarTypeId());
            ps.setString(4, entity.getCarLocationId());
            ps.setBoolean(5, entity.getIsActive());
            ps.setInt(6, entity.getId());
        });
    }

    @Override
    public boolean remove(Car entity) {
        return createUpdate(DELETE_CAR, ps -> ps.setInt(1, entity.getId()));
    }

    private EntityMapper<Car> getMapper() {
        return resultSet -> new Car(resultSet.getInt(COLUMN_ID), resultSet.getString(COLUMN_CAR_NAME),
                resultSet.getString(COLUMN_PHOTO_URL), resultSet.getInt(COLUMN_CAR_TYPE_ID), resultSet.getString(COLUMN_LOCATION_ID), resultSet.getInt(COLUMN_IS_ACTIVE));
    }

    private List<Car> executeCustomQuering(String query, StatementMapper<Car> statementMapper, EntityMapper<Car> mapper) {
        List<Car> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = DataSourceFactory.getPreparedStatement(query);) {
            statementMapper.map(preparedStatement);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Car entity = mapper.map(resultSet);

                    result.add(entity);
                }
            } catch (SQLException e) {
                log.error("Could not create entity!!", e);
            }
        } catch (SQLException e) {
            log.error("Could not create preparedStatement!!", e);
        }
        return result;
    }
}
