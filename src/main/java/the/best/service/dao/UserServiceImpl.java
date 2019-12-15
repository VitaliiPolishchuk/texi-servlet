package the.best.service.dao;

import lombok.extern.slf4j.Slf4j;
import the.best.dao.EntityDao;
import the.best.dao.UserDAO;
import the.best.entity.User;
import the.best.enums.DaoType;
import the.best.pattern.factory.DaoFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Pattern;

@Slf4j
public class UserServiceImpl implements UserService {

    UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = (UserDAO) DaoFactory.getEntityDao(DaoType.USER);
    }

    public static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private final static double PERCENT_PRICE_TO_POINTS = 0.1;

    public UserServiceImpl() {
        this.userDAO = (UserDAO) DaoFactory.getEntityDao(DaoType.USER);
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User doLogin(User user) { // authentification
        return userDAO.getByEmailAndPassword(user);
    }

    @Override
    public void updateUserPoints(User user, int price, boolean isUsedDiscount, Connection connection) throws SQLException {
        log.info("Updating user points " + user);
        int currentPoints = user.getPoints();
        if (isUsedDiscount) {
            currentPoints -= POINTS_REQUIRE_TO_DISCOUNT;
        }
        user.setPoints(calculatePoints(currentPoints, price));
        userDAO.update(user, connection);
        log.info("Updated user points " + user);
    }

    @Override
    public boolean validate(User user) {
        if (!EMAIL_PATTERN.matcher(user.getEmail()).matches()) {
            return false;
        }
        if (userDAO.getById(user.getEmail()) != null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean create(User user) {
        return userDAO.create(user);
    }


    private int calculatePoints(int curPoints, int price) {
        return (int) (curPoints + price * PERCENT_PRICE_TO_POINTS);
    }

}
