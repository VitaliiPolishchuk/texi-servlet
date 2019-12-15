package the.best.pattern.wrapper;

import the.best.entity.Discount;
import the.best.web.data.Order;
import the.best.entity.User;

public interface PriceCalculationService {
    void setNext(PriceCalculationService transactionHandler);

    void calculate(Order order, User user, Discount discount);
}
