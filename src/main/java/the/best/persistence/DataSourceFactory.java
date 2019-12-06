package the.best.persistence;

import com.mysql.cj.jdbc.MysqlDataSource;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
public class DataSourceFactory {

    private static final DataSourceFactory INSTANCE = new DataSourceFactory();

    private static DataSource dataSource;

    private DataSourceFactory() {

    }


    static {
        Properties properties = new Properties();
        try {
            properties.load(DataSourceFactory.class.getResourceAsStream("/db.properties"));
            MysqlDataSource mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setURL(properties.getProperty("DB_URL"));
            mysqlDataSource.setUser(properties.getProperty("DB_USERNAME"));
            mysqlDataSource.setPassword(properties.getProperty("DB_PASSWORD"));
            dataSource = mysqlDataSource;
            log.info("DabaSource created: " + dataSource);

        } catch (IOException e) {
            log.error("Error while reading properties from file!", e);
        }


    }

    public static DataSourceFactory getInstance(){
        return INSTANCE;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            log.error("Error while connection creation", e);
        }
        return connection;
    }
}
