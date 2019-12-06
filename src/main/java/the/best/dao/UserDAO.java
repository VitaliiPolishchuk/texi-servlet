package the.best.dao;

import lombok.extern.slf4j.Slf4j;
import the.best.entity.User;
import the.best.persistence.DataSourceFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class UserDAO {
    public static final String TABLE = "user";

    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_EMAIL_PASSWORD = "email_password";

    private static final String QUERY_USER_BY_EMAIL = "SELECT * FROM " + TABLE +
            " WHERE " + COLUMN_EMAIL + " = ? AND " +
            COLUMN_EMAIL_PASSWORD + " = ?";

    private static final String INSERT_USER = "INSERT INTO " + TABLE +
            " VALUES(?, ?, ?, ?)";

    private static final DataSourceFactory dataSourceFactory = DataSourceFactory.getInstance();

    public User get(User user){
        try(PreparedStatement queryUserByEmailPrepareStatement = dataSourceFactory.getConnection().prepareStatement(QUERY_USER_BY_EMAIL)){
            queryUserByEmailPrepareStatement.setString(1, user.getEmail());
            queryUserByEmailPrepareStatement.setString(2, user.getEmailPassword());
            ResultSet resultSet = queryUserByEmailPrepareStatement.executeQuery();
            if(resultSet.next()){
                user.setFirstName(resultSet.getString(COLUMN_FIRST_NAME));
                user.setLastName(resultSet.getString(COLUMN_LAST_NAME));

                return user;
            }

        } catch (SQLException e){
            log.error("Failed find all cars by type id " + e.getMessage());
        }
        return null;
    }

    public boolean insert(User user){
        try(PreparedStatement insertUserPreparedStatement = dataSourceFactory.getConnection().prepareStatement(INSERT_USER)){
            insertUserPreparedStatement.setString(1, user.getEmail());
            insertUserPreparedStatement.setString(2, user.getFirstName());
            insertUserPreparedStatement.setString(3, user.getLastName());
            insertUserPreparedStatement.setString(4, user.getEmailPassword());

            insertUserPreparedStatement.execute();

        } catch (SQLException e){
            log.error("Failed insert user " + e.getMessage());
        }
        return false;
    }
}

