package the.best.dao;

import lombok.extern.slf4j.Slf4j;
import the.best.entity.Car;
import the.best.entity.User;
import the.best.enums.DaoType;
import the.best.pattern.factory.DaoFactory;
import the.best.persistence.DataSourceFactory;
import the.best.service.dao.UserService;
import the.best.service.dao.UserServiceImpl;
import the.best.web.data.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;

@Slf4j
public class OrderDAO extends AbstractDao<Order, Integer> {
    public static final String TABLE = "drive_order";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ORIGIN_ID = "origin_id";
    public static final String COLUMN_DESTINATION_ID = "destination_id";
    public static final String COLUMN_CAR_ID = "car_id";
    public static final String COLUMN_PRICE = "price";

    private static final String QUERY_ALL = "SELECT * FROM " + TABLE;

    UserService userService = new UserServiceImpl();
    private static final CarDAO carDAO = (CarDAO) DaoFactory.getEntityDao(DaoType.CAR);

    private static final String INSERT_ORDER = "INSERT INTO " + TABLE +
            " (" + COLUMN_ORIGIN_ID + ", " + COLUMN_DESTINATION_ID + ", " + COLUMN_CAR_ID + ", " +
            COLUMN_PRICE + ") VALUES(?,?,?,?)";

    private static final DataSourceFactory dataSourceFactory = DataSourceFactory.getInstance();

    public void save(Order order, Connection connection) {
        log.info("Insert order " + order);
        try (PreparedStatement insertOrderPreparedStatement = connection.prepareStatement(INSERT_ORDER)) {
            insertOrderPreparedStatement.setString(1, order.getOrigin().getLocationName());
            insertOrderPreparedStatement.setString(2, order.getDestination().getLocationName());
            insertOrderPreparedStatement.setInt(3, order.getCar().getId());
            insertOrderPreparedStatement.setDouble(4, order.getDiscountPrice());

            insertOrderPreparedStatement.executeUpdate();
            log.info("Order was inserted " + order);

        } catch (SQLException e) {
            log.error("Failed insert user " + e.getMessage());
        }
    }

    @Override
    public boolean create(Order entity) {
        return false;
    }

    @Override
    public boolean update(Order entity) {
        return false;
    }

    @Override
    public boolean remove(Order entity) {
        return false;
    }
}
