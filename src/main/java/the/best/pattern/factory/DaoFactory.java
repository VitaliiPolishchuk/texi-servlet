package the.best.pattern.factory;

import the.best.dao.*;
import the.best.enums.DaoType;

import java.util.HashMap;
import java.util.Map;

public class DaoFactory {

    private static Map<DaoType, EntityDao> daoMap = new HashMap<>();

    static {
        daoMap.put(DaoType.USER, new UserDAO());
        daoMap.put(DaoType.CAR, new CarDAO());
        daoMap.put(DaoType.CAR_TYPE, new CarTypeDAO());
        daoMap.put(DaoType.DISCOUNT, new DiscountDAO());
        daoMap.put(DaoType.ORDER, new OrderDAO());
    }

    private DaoFactory() {
    }

    public static EntityDao getEntityDao(DaoType daoType) {
        EntityDao entityDao = daoMap.get(daoType);
        if (entityDao != null) {
            return entityDao;
        }
        throw new RuntimeException("Dao with current dao type do not exist: " + daoType.name());
    }
}
