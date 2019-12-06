package the.best.pattern;

import the.best.dao.UserPointsDAO;
import the.best.entity.Discount;
import the.best.service.UserService;
import the.best.service.UserServiceImpl;
import the.best.web.data.Order;
import the.best.entity.User;

public class UserPointsPriceCalculationService extends BasePriceCalculationService{

    UserPointsDAO userPointsDAO = new UserPointsDAO();
    public static final double DISCOUNT_PERCENT = 0.1;


    @Override
    public void calculate(Order order, User user, Discount discount) {

        int points = userPointsDAO.get(user);;

        if(isEmoughtPoints(points)) {
            double delta = order.getWholePrice() * DISCOUNT_PERCENT;
            order.setDiscountPrice(order.getDiscountPrice() - delta);
            order.setUserPoints(true);
        }

        userPointsDAO.get(user);
        if(next != null) {
            next.calculate(order, user, discount);
        }
    }

    private boolean isEmoughtPoints(int points){
        return points >= UserService.POINTS_REQUIRE_TO_DISCOUNT;
    }
}
