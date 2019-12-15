package the.best.pattern.wrapper;

import lombok.extern.slf4j.Slf4j;
import the.best.entity.Discount;
import the.best.web.data.Order;
import the.best.entity.User;

@Slf4j
public class BookingPriceCalculationService extends BasePriceCalculationService {

    @Override
    public void calculate(Order order, User user, Discount discount) {
        log.info("Calculating booking price for order " + order);
        order.setDiscountPrice(order.getDiscountPrice() + order.getCarType().getPriceBooking());
        order.setWholePrice(order.getWholePrice() + order.getCarType().getPriceBooking());
        log.info("Calculated booking price for order " + order);

        if (next != null) {
            next.calculate(order, user, discount);
        }
    }
}
