package the.best.dao;

import lombok.extern.slf4j.Slf4j;
import the.best.entity.Discount;
import the.best.persistence.DataSourceFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class DiscountDAO {
    public static final String TABLE = "discount";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PERCENT = "percent";

    private static final String QUERY_BY_ID = "SELECT * FROM " + TABLE + " WHERE " + COLUMN_ID + " = ?";

    private static final DataSourceFactory dataSourceFactory = DataSourceFactory.getInstance();

    public Discount get(String id){
        try(PreparedStatement queryUserByEmailPrepareStatement = dataSourceFactory.getConnection().prepareStatement(QUERY_BY_ID)){
            queryUserByEmailPrepareStatement.setString(1, id);
            ResultSet resultSet = queryUserByEmailPrepareStatement.executeQuery();
            if(resultSet.next()){
                Discount res = new Discount();
                res.setId(id);
                res.setPercent(resultSet.getDouble(COLUMN_PERCENT));
                return res;
            }
            return null;

        } catch (SQLException e){
            log.error("Failed find discount by id " + e.getMessage());
        }
        return null;
    }
}
