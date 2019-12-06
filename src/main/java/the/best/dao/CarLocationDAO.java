package the.best.dao;

import lombok.extern.slf4j.Slf4j;
import the.best.persistence.DataSourceFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
public class CarLocationDAO {
    public static final String TABLE = "car_location";

    public static final String COLUMN_CAR_ID = "car_id";
    public static final String COLUMN_LOCATION_ID = "location_id";

    private static final DataSourceFactory dataSourceFactory = DataSourceFactory.getInstance();

    private static final String DELETE_CAR_LOCATION = "DELETE FROM " + TABLE + " WHERE " + COLUMN_CAR_ID + " = ?";


    public void delete(int car_id){
        try(PreparedStatement deleteActiveCarPreparedStatement = dataSourceFactory.getConnection().prepareStatement(DELETE_CAR_LOCATION)){
            deleteActiveCarPreparedStatement.setInt(1, car_id);

            deleteActiveCarPreparedStatement.execute();

        } catch (SQLException e){
            log.error("Failed delete car_location " + e.getMessage());
        }
    }


}
