package the.best.pattern;

import the.best.entity.Discount;
import the.best.web.data.Order;
import the.best.entity.User;

public class BookingPriceCalculationService extends BasePriceCalculationService {

    @Override
    public void calculate(Order order, User user, Discount discount) {
        order.setDiscountPrice(order.getDiscountPrice() + order.getCarType().getPriceBooking());
        order.setWholePrice(order.getWholePrice() + order.getCarType().getPriceBooking());


        if(next != null) {
            next.calculate(order, user, discount);
        }
    }
}
