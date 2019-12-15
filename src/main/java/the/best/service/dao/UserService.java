package the.best.service.dao;

import the.best.entity.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserService {

    public static final int POINTS_REQUIRE_TO_DISCOUNT = 100;

    User doLogin(User user);

    void updateUserPoints(User user, int price, boolean isUsedDiscount, Connection connection) throws SQLException;

    boolean validate(User user);

    boolean create(User user);
}
