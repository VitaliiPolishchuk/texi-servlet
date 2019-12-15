package the.best.pattern.wrapper;

import lombok.extern.slf4j.Slf4j;
import the.best.entity.Discount;
import the.best.service.dao.UserService;
import the.best.web.data.Order;
import the.best.entity.User;

@Slf4j
public class UserPointsPriceCalculationService extends BasePriceCalculationService {

    public static final double DISCOUNT_PERCENT = 0.1;


    @Override
    public void calculate(Order order, User user, Discount discount) {
        log.info("Calculating user points price price for order " + order);
        int points = user.getPoints();
        ;

        if (isEmoughtPoints(points)) {
            double delta = order.getWholePrice() * DISCOUNT_PERCENT;
            order.setDiscountPrice(order.getDiscountPrice() - delta);
            order.setUserPoints(true);
        }
        log.info("Calculated user points price price for order " + order);
        if (next != null) {
            next.calculate(order, user, discount);
        }
    }

    private boolean isEmoughtPoints(int points) {
        return points >= UserService.POINTS_REQUIRE_TO_DISCOUNT;
    }
}
