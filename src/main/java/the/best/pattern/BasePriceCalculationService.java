package the.best.pattern;

import the.best.entity.Discount;
import the.best.web.data.Order;
import the.best.entity.User;

abstract public class BasePriceCalculationService implements PriceCalculationService{
    PriceCalculationService next;

    @Override
    public void setNext(PriceCalculationService transactionHandler){
        next = transactionHandler;
    }

    @Override
    abstract public void calculate(Order order, User user, Discount discount);
}
