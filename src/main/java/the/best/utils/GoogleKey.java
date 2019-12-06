package the.best.utils;

import lombok.extern.slf4j.Slf4j;
import the.best.persistence.DataSourceFactory;

import java.io.IOException;
import java.util.Properties;

@Slf4j
public class GoogleKey {
    public static String MAPS_API_KEY;

    static {
        Properties properties = new Properties();
        try {
            properties.load(DataSourceFactory.class.getResourceAsStream("/googleApi.properties"));
            MAPS_API_KEY = properties.getProperty("API_KEY");
        } catch (IOException e) {
            log.error("Error while reading properties from file!", e);
        }
    }

}
