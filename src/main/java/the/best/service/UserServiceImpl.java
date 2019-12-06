package the.best.service;

import the.best.dao.UserDAO;
import the.best.dao.UserPointsDAO;
import the.best.entity.User;

public class UserServiceImpl implements UserService {

    UserDAO userDAO = new UserDAO();
    UserPointsDAO userPointsDAO = new UserPointsDAO();
    private final static double PERCENT_PRICE_TO_POINTS = 0.1;

    @Override
    public User validate(User user) { // authentification
        return userDAO.get(user);
    }

    @Override
    public void updateUserPoints(User user, int price, boolean isUsedDiscount) {
        int currentPoints = userPointsDAO.get(user);
        if(isUsedDiscount){
            currentPoints -= POINTS_REQUIRE_TO_DISCOUNT;
        }
        userPointsDAO.update(user, calculatePoints(currentPoints, price));
    }


    private int calculatePoints(int curPoints, int price){
        return (int)(curPoints + price * PERCENT_PRICE_TO_POINTS);
    }

}
