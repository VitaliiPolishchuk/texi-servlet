package the.best.dao;

import lombok.extern.slf4j.Slf4j;
import the.best.entity.User;
import the.best.persistence.DataSourceFactory;
import the.best.service.UserService;
import the.best.service.UserServiceImpl;
import the.best.web.data.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
public class OrderDAO {
    public static final String TABLE = "drive_order";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ORIGIN_ID = "origin_id";
    public static final String COLUMN_DESTINATION_ID = "destination_id";
    public static final String COLUMN_CAR_ID = "car_id";
    public static final String COLUMN_PRICE = "price";

    UserService userService = new UserServiceImpl();
    CarActiveDAO carActiveDAO = new CarActiveDAO();

    private static final String INSERT_ORDER = "INSERT INTO " + TABLE +
            " (" + COLUMN_ORIGIN_ID + ", " + COLUMN_DESTINATION_ID + ", " + COLUMN_CAR_ID + ", " +
            COLUMN_PRICE + ") VALUES(?,?,?,?)";

    private static final DataSourceFactory dataSourceFactory = DataSourceFactory.getInstance();

    public void save(Order order, User user){
        Connection connection = dataSourceFactory.getConnection();
        try{
            connection.setAutoCommit(false);
            save(order);
            carActiveDAO.delete(order.getCar().getId());
            userService.updateUserPoints(user, (int)order.getDiscountPrice(), order.isUserPoints());
        } catch (SQLException e) {
            log.error("Error to make transaction (save order)" + e.getMessage());
            e.printStackTrace();
            try{
                connection.rollback();
            } catch (SQLException ex) {
                log.error("Things are really badly" + ex.getMessage());
                ex.printStackTrace();
            }

        } finally {
            try{
                log.info("Resetting default commit behaviour");
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                log.error("Couldn`t reset autocommit" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void save(Order order){
        try(PreparedStatement insertOrderPreparedStatement = dataSourceFactory.getConnection().prepareStatement(INSERT_ORDER)){
            insertOrderPreparedStatement.setString(1, order.getOrigin().getLocationName());
            insertOrderPreparedStatement.setString(2, order.getDestination().getLocationName());
            insertOrderPreparedStatement.setInt(3, order.getCar().getId());
            insertOrderPreparedStatement.setDouble(4, order.getDiscountPrice());

            insertOrderPreparedStatement.execute();

        } catch (SQLException e){
            log.error("Failed insert user " + e.getMessage());
        }
    }
}
