package the.best.pattern;

import the.best.entity.Discount;
import the.best.web.data.Order;
import the.best.entity.User;

public class KmPriceCalculationService extends BasePriceCalculationService {
    @Override
    public void calculate(Order order, User user, Discount discount) {
        double money = (order.getTimeToReachDestination() - order.getTimeToReachOrigin()) / 60;
        order.setDiscountPrice(order.getDiscountPrice() + money);
        order.setWholePrice(order.getWholePrice() + money);

        if(next != null) {
            next.calculate(order, user, discount);
        }
    }
}
