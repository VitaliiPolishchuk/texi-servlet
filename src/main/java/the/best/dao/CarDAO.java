package the.best.dao;

import lombok.extern.slf4j.Slf4j;
import the.best.entity.Car;
import the.best.entity.CarType;
import the.best.entity.Location;
import the.best.web.data.Order;
import the.best.entity.builder.OrderBuilder;
import the.best.persistence.DataSourceFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CarDAO {
    public static final String TABLE = "car";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CAR_NAME = "car_name";
    public static final String COLUMN_PHOTO_URL = "photo_url";
    public static final String COLUMN_CAR_TYPE_ID = "car_type_id";

    private static final String QUERY_ALL_WITH_LOCATION_ACTIVE_CAR_BY_TYPE_ID = "SELECT " + TABLE + "." + COLUMN_ID +
            ", " + TABLE + "." + COLUMN_CAR_NAME + ", " + TABLE + "." + COLUMN_PHOTO_URL + ", " +
            TABLE + "." + COLUMN_CAR_TYPE_ID + ", " + CarLocationDAO.TABLE + "." + CarLocationDAO.COLUMN_LOCATION_ID +
            " FROM " + TABLE +
            " INNER JOIN " + CarLocationDAO.TABLE + " ON " + TABLE + "." + COLUMN_ID +
            " = " + CarLocationDAO.TABLE + "." + CarLocationDAO.COLUMN_CAR_ID + " WHERE " + COLUMN_CAR_TYPE_ID + " = ? AND " + COLUMN_ID + " IN (" +
            "SELECT * FROM " + CarActiveDAO.TABLE + ")";

    private static final String QUERY_ALL_CARS = "SELECT * FROM " + TABLE;
    private static final String DELETE_CAR = "DELETE FROM " + TABLE + " WHERE id = ?";
    private static final String QUERY_ALL_CAR_BY_TYPE_ID = "SELECT * FROM " + TABLE + " WHERE " + COLUMN_CAR_TYPE_ID + " = ?";

    private static final DataSourceFactory dataSourceFactory = DataSourceFactory.getInstance();

    private CarActiveDAO carActiveDAO = new CarActiveDAO();
    private CarLocationDAO carLocationDAO = new CarLocationDAO();

    public List<Order> getAllWithLocationIdActiveByTypeId(int carTypeId){
        log.info("query all with location active car by type id = " + QUERY_ALL_WITH_LOCATION_ACTIVE_CAR_BY_TYPE_ID);
        try(PreparedStatement deleteRoomPreparedStatement = dataSourceFactory.getConnection().prepareStatement(QUERY_ALL_WITH_LOCATION_ACTIVE_CAR_BY_TYPE_ID)){
            deleteRoomPreparedStatement.setInt(1, (int)carTypeId);
            ResultSet resultSet = deleteRoomPreparedStatement.executeQuery();
            List<Order> res = new ArrayList<>();
            while(resultSet.next()){
                Car car = new Car();
                car.setId(resultSet.getInt(1));
                car.setCarName(resultSet.getString(2));
                car.setPhotoUrl(resultSet.getString(3));
                car.setCarTypeId(resultSet.getInt(4));
                Location location = new Location(resultSet.getString(5));

                Order order = new Order(new OrderBuilder().setCar(car).setCarLocation(location));

                res.add(order);
            }

            return res;

        } catch (SQLException e){
            log.error("Failed find all active cars by type id " + e.getMessage());
            return null;
        }
    }

    public List<Car> getAll() {
        try (Statement statement = dataSourceFactory.getConnection().createStatement();

             ResultSet resultSet = statement.executeQuery(QUERY_ALL_CARS)){
            List<Car> res = new ArrayList<>();
            while(resultSet.next()){
                Car car = new Car();
                car.setId(resultSet.getInt(COLUMN_ID));
                car.setCarName(resultSet.getString(COLUMN_CAR_NAME));
                car.setPhotoUrl(resultSet.getString(COLUMN_PHOTO_URL));
                car.setCarTypeId(resultSet.getInt(COLUMN_CAR_TYPE_ID));

                res.add(car);
            }
            return res;
        } catch (SQLException e){
            log.error("Query all car failed " + e.getMessage());
            return null;
        }
    }

    public List<Car> getAllByCarTypeId(int carTypeId) {
        log.info("query all with location active car by type id = " + QUERY_ALL_WITH_LOCATION_ACTIVE_CAR_BY_TYPE_ID);
        try(PreparedStatement deleteRoomPreparedStatement = dataSourceFactory.getConnection().prepareStatement(QUERY_ALL_CAR_BY_TYPE_ID)){
            deleteRoomPreparedStatement.setInt(1, (int)carTypeId);
            ResultSet resultSet = deleteRoomPreparedStatement.executeQuery();
            List<Car> res = new ArrayList<>();
            while(resultSet.next()){
                Car car = new Car();
                car.setId(resultSet.getInt(COLUMN_ID));
                car.setCarName(resultSet.getString(COLUMN_CAR_NAME));
                car.setPhotoUrl(resultSet.getString(COLUMN_PHOTO_URL));
                car.setCarTypeId(resultSet.getInt(COLUMN_CAR_TYPE_ID));

                res.add(car);
            }

            return res;

        } catch (SQLException e){
            log.error("Failed find all active cars by type id " + e.getMessage());
            return null;
        }
    }

    public void delete(int id){

        carActiveDAO.delete(id);
        carLocationDAO.delete(id);

        try(PreparedStatement deleteCarPrepareStatement = dataSourceFactory.getConnection().prepareStatement(DELETE_CAR)){
            deleteCarPrepareStatement.setInt(1, id);
            deleteCarPrepareStatement.execute();

        } catch (SQLException e){
            log.error("Failed delete car type " + e.getMessage());
        }
    }
}
