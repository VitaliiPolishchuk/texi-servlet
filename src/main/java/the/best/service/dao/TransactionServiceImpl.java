package the.best.service.dao;

import lombok.extern.slf4j.Slf4j;
import the.best.dao.CarDAO;
import the.best.dao.OrderDAO;
import the.best.entity.Car;
import the.best.entity.User;
import the.best.persistence.DataSourceFactory;
import the.best.web.data.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;


@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private static final DataSourceFactory dataSourceFactory = DataSourceFactory.getInstance();
    private CarDAO carDAO = new CarDAO();
    private UserService userService = new UserServiceImpl();
    private OrderDAO orderDAO = new OrderDAO();

    @Override
    public void processOrder(Order order, User user) {
        log.info("Executing process order transaction with order " + order);
        Connection connection = dataSourceFactory.getConnection();
        try {
            Savepoint save1 = connection.setSavepoint();
            connection.setAutoCommit(false);
            orderDAO.save(order, connection);
            Car car = order.getCar();
            car.setIsActive(false);
            carDAO.update(car, connection);
            userService.updateUserPoints(user, (int) order.getDiscountPrice(), order.isUserPoints(), connection);
            connection.commit();
        } catch (SQLException e) {
            log.error("Error to make transaction (save order)" + e.getMessage());
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.error("Things are really badly" + ex.getMessage());
                ex.printStackTrace();
            }

        } finally {
            try {
                log.info("Resetting default commit behaviour");
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                log.error("Couldn`t reset autocommit" + e.getMessage());
                e.printStackTrace();
            }
        }
        log.info("Executed process order transaction with order " + order);
    }
}
