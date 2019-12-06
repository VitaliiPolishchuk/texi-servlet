package the.best.dao;

import lombok.extern.slf4j.Slf4j;
import the.best.entity.User;
import the.best.persistence.DataSourceFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class UserPointsDAO {
    public static final String TABLE = "user_points";

    public static final String COLUMN_USER_EMAIL = "user_email";
    public static final String COLUMN_AMOUNT = "amount";

    private static final String QUERY_USER_POINTS_BY_USER_ID = "SELECT * FROM " + TABLE + " WHERE " +
        COLUMN_USER_EMAIL + " = ?";

    private static final String UPDATE_USER_POINTS = "UPDATE " + TABLE + " SET " +
            COLUMN_AMOUNT + " = ? WHERE " + COLUMN_USER_EMAIL + " = ?";

    private static final DataSourceFactory dataSourceFactory = DataSourceFactory.getInstance();

    public int get(User user){
        try(PreparedStatement queryUserByEmailPrepareStatement = dataSourceFactory.getConnection().prepareStatement(QUERY_USER_POINTS_BY_USER_ID)){
            queryUserByEmailPrepareStatement.setString(1, user.getEmail());
            ResultSet resultSet = queryUserByEmailPrepareStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt(COLUMN_AMOUNT);
            }

        } catch (SQLException e){
            log.error("Failed query user points by user id " + e.getMessage());
        }
        return -1;
    }

    public void update(User user, int points){
        try(PreparedStatement queryUserByEmailPrepareStatement = dataSourceFactory.getConnection().prepareStatement(UPDATE_USER_POINTS)){
            queryUserByEmailPrepareStatement.setInt(1, points);
            queryUserByEmailPrepareStatement.setString(2, user.getEmail());

            queryUserByEmailPrepareStatement.execute();

        } catch (SQLException e){
            log.error("Failed update user points " + e.getMessage());
        }
    }
}
