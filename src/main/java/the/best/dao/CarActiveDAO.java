package the.best.dao;

import lombok.extern.slf4j.Slf4j;
import the.best.persistence.DataSourceFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
public class CarActiveDAO {
    public static final String TABLE = "car_active";

    public static final String COLUMN_CAR_ID = "car_id";

    public static final String DELETE_CAR = "DELETE FROM " + TABLE + " WHERE " + COLUMN_CAR_ID + " = ? ";

    private static final DataSourceFactory dataSourceFactory = DataSourceFactory.getInstance();

    public void delete(int car_id){
        try(PreparedStatement deleteActiveCarPreparedStatement = dataSourceFactory.getConnection().prepareStatement(DELETE_CAR)){
            deleteActiveCarPreparedStatement.setInt(1, car_id);

            deleteActiveCarPreparedStatement.execute();

        } catch (SQLException e){
            log.error("Failed delete car_active " + e.getMessage());
        }
    }
}
