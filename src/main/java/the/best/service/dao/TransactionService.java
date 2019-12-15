package the.best.service.dao;

import the.best.entity.User;
import the.best.web.data.Order;

public interface TransactionService {
    void processOrder(Order order, User user);
}
