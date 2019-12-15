package the.best.dao;

import lombok.extern.slf4j.Slf4j;
import the.best.entity.CarType;
import the.best.entity.User;
import the.best.persistence.DataSourceFactory;
import the.best.web.data.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
public class UserDAO extends AbstractDao<User, String> {
    public static final String TABLE = "user";

    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_EMAIL_PASSWORD = "email_password";
    public static final String COLUMN_USER_POINTS = "user_points";

    private static final String QUERY_BY_ID = "SELECT * FROM " + TABLE +
            " WHERE " + COLUMN_EMAIL + " = ?";

    private static final String QUERY_USER_BY_EMAIL_AND_PASSWROD = "SELECT * FROM " + TABLE +
            " WHERE " + COLUMN_EMAIL + " = ? AND " +
            COLUMN_EMAIL_PASSWORD + " = ?";

    private static final String INSERT_USER = "INSERT INTO " + TABLE +
            " VALUES(?, ?, ?, ?, ?)";

    private static final String UPDATE_USER = "UPDATE " + TABLE + " SET " +
            COLUMN_FIRST_NAME + " = ?, " +
            COLUMN_LAST_NAME + " = ?, " +
            COLUMN_EMAIL_PASSWORD + " = ?, " +
            COLUMN_USER_POINTS + " = ? WHERE " + COLUMN_EMAIL + " = ?";


    private static final DataSourceFactory dataSourceFactory = DataSourceFactory.getInstance();

    public User getById(String id) {
        return getById(QUERY_BY_ID, ps -> ps.setString(1, id), getMapper());
    }

    public User getByEmailAndPassword(User user) {
        log.info("Getting user by id " + user.getEmail());
        try (PreparedStatement queryUserByEmailPrepareStatement = dataSourceFactory.getConnection().prepareStatement(QUERY_USER_BY_EMAIL_AND_PASSWROD)) {
            queryUserByEmailPrepareStatement.setString(1, user.getEmail());
            queryUserByEmailPrepareStatement.setString(2, user.getEmailPassword());
            ResultSet resultSet = queryUserByEmailPrepareStatement.executeQuery();
            if (resultSet.next()) {
                user.setFirstName(resultSet.getString(COLUMN_FIRST_NAME));
                user.setLastName(resultSet.getString(COLUMN_LAST_NAME));
                user.setEmail(resultSet.getString(COLUMN_EMAIL));
                user.setEmailPassword(resultSet.getString(COLUMN_EMAIL_PASSWORD));
                user.setPoints(resultSet.getInt(COLUMN_USER_POINTS));
                log.info("user founded " + user.getEmail());
                return user;
            }

        } catch (SQLException e) {
            log.error("Failed get user by id " + e.getMessage());
        }
        return null;
    }

    public boolean update(User user, Connection connection) throws SQLException {
        log.info("Updating user by transaction " + user.getEmail());
        PreparedStatement insertUserPreparedStatement = connection.prepareStatement(UPDATE_USER);
        insertUserPreparedStatement.setString(1, user.getFirstName());
        insertUserPreparedStatement.setString(2, user.getLastName());
        insertUserPreparedStatement.setString(3, user.getEmailPassword());
        insertUserPreparedStatement.setInt(4, user.getPoints());
        insertUserPreparedStatement.setString(5, user.getEmail());
        insertUserPreparedStatement.executeUpdate();
        log.info("User is updated with id " + user.getEmail());
        return false;
    }

    @Override
    public boolean create(User entity) {
        return createUpdate(INSERT_USER, ps -> {
            ps.setString(1, entity.getEmail());
            ps.setString(2, entity.getFirstName());
            ps.setString(3, entity.getLastName());
            ps.setString(4, entity.getEmailPassword());
            ps.setInt(5, entity.getPoints());
        });
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public boolean remove(User entity) {
        return false;
    }

    private EntityMapper<User> getMapper() {
        return resultSet -> new User(resultSet.getString(COLUMN_FIRST_NAME), resultSet.getString(COLUMN_LAST_NAME),
                resultSet.getString(COLUMN_EMAIL), resultSet.getString(COLUMN_EMAIL_PASSWORD), resultSet.getInt(COLUMN_USER_POINTS));
    }
}

