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
public class CarTypeDAO {

    public static final String TABLE = "car_type";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TYPE_NAME = "type_name";
    public static final String COLUMN_PRICE_BOOKING = "price_booking";
    public static final String COLUMN_PRICE_PER_KM = "price_per_km";
    public static final String COLUMN_CAPACITY = "capacity";

    private static final String QUERY_CARS_TYPE = "SELECT * FROM " + TABLE;

    private static final String DELETE_CAR_TYPE = "DELETE FROM " + TABLE + " WHERE " + COLUMN_ID + " = ?";

    private static final DataSourceFactory dataSourceFactory = DataSourceFactory.getInstance();

    private CarDAO carDAO = new CarDAO();

    public List<CarType> getAll() {
        try (Statement statement = dataSourceFactory.getConnection().createStatement();

             ResultSet resultSet = statement.executeQuery(QUERY_CARS_TYPE)){
            List<CarType> res = new ArrayList<>();
            while(resultSet.next()){
                CarType carType = new CarType();
                carType.setId(resultSet.getInt(COLUMN_ID));
                carType.setTypeName(resultSet.getString(COLUMN_TYPE_NAME));
                carType.setPriceBooking(resultSet.getDouble(COLUMN_PRICE_BOOKING));
                carType.setPricePerKm(resultSet.getDouble(COLUMN_PRICE_PER_KM));
                carType.setCapacity(resultSet.getInt(COLUMN_CAPACITY));

                res.add(carType);
            }
            return res;
        } catch (SQLException e){
            System.out.println("Query failed " + e.getMessage());
            return null;
        }
    }

    public void delete(int id){

        List<Car> cars = carDAO.getAllByCarTypeId(id);
        for(Car car : cars){
            carDAO.delete(car.getId());
        }

        try(PreparedStatement insertUserPreparedStatement = dataSourceFactory.getConnection().prepareStatement(DELETE_CAR_TYPE)){
            insertUserPreparedStatement.setInt(1, id);
            insertUserPreparedStatement.execute();
        } catch (SQLException e){
            log.error("Failed delete car type " + e.getMessage());
        }
    }
}
