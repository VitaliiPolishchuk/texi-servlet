package the.best.service.dao;

import the.best.dao.DiscountDAO;
import the.best.dao.EntityDao;
import the.best.entity.Discount;
import the.best.enums.DaoType;
import the.best.pattern.factory.DaoFactory;

public class DiscountServiceImpl implements DiscountService {

    private EntityDao<Discount, String> discountDAO;

    public DiscountServiceImpl() {
        discountDAO = DaoFactory.getEntityDao(DaoType.DISCOUNT);
    }

    @Override
    public Discount getById(String id) {
        return discountDAO.getById(id);
    }
}
