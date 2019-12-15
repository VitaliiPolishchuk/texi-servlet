package the.best.pattern.wrapper;

import lombok.extern.slf4j.Slf4j;
import the.best.entity.Discount;
import the.best.web.data.Order;
import the.best.entity.User;

@Slf4j
public class KmPriceCalculationService extends BasePriceCalculationService {
    @Override
    public void calculate(Order order, User user, Discount discount) {
        log.info("Calculating km price price for order " + order);
        double money = (order.getTimeToReachDestination() - order.getTimeToReachOrigin()) / 60;
        order.setDiscountPrice(order.getDiscountPrice() + money);
        order.setWholePrice(order.getWholePrice() + money);
        log.info("Calculated km price for order " + order);

        if (next != null) {
            next.calculate(order, user, discount);
        }
    }
}
