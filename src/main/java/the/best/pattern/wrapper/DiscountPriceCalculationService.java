package the.best.pattern.wrapper;

import lombok.extern.slf4j.Slf4j;
import the.best.entity.Discount;
import the.best.web.data.Order;
import the.best.entity.User;

@Slf4j
public class DiscountPriceCalculationService extends BasePriceCalculationService {

    @Override
    public void calculate(Order order, User user, Discount discount) {
        log.info("Calculating discount price for order " + order);
        if (discount != null) {
            double delta = order.getWholePrice() * discount.getPercent();
            order.setDiscountPrice(order.getDiscountPrice() - delta);
            order.setDiscount(true);
        }
        log.info("Calculated discount price for order " + order);
        if (next != null) {
            next.calculate(order, user, discount);
        }
    }
}
