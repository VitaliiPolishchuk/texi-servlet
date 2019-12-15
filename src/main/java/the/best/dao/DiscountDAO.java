package the.best.dao;

import lombok.extern.slf4j.Slf4j;
import the.best.entity.CarType;
import the.best.entity.Discount;
import the.best.persistence.DataSourceFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class DiscountDAO extends AbstractDao<Discount, String> {
    public static final String TABLE = "discount";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PERCENT = "percent";

    private static final String QUERY_BY_ID = "SELECT * FROM " + TABLE + " WHERE " + COLUMN_ID + " = ?";

    public Discount getById(String id){
        return getById(QUERY_BY_ID, ps -> ps.setString(1, id), getMapper());
    }

    @Override
    public boolean create(Discount entity) {
        return false;
    }

    @Override
    public boolean update(Discount entity) {
        return false;
    }

    @Override
    public boolean remove(Discount entity) {
        return false;
    }

    private EntityMapper<Discount> getMapper() {
        return resultSet -> new Discount(resultSet.getString(COLUMN_ID), resultSet.getDouble(COLUMN_PERCENT));
    }
}
