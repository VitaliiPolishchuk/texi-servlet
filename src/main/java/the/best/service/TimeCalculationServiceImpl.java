package the.best.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import the.best.utils.GoogleKey;
import the.best.entity.Location;
import the.best.request.HttpService;
import the.best.request.HttpServiceImpl;

import java.net.URISyntaxException;

@Slf4j
public class TimeCalculationServiceImpl implements TimeCalculationService {

    private static final String TIME_BETWEEN_TWO_POINT_URL = "https://maps.googleapis.com/maps/api/distancematrix/json";

    private static final String DESTINATIONS_PARAM = "destinations";
    private static final String ORIGINS_PARAM = "origins";
    private static final String KEY_PARAM = "key";
    private static final String UNITS_PARAM = "units";

    private static HttpService httpService = new HttpServiceImpl();

    public TimeCalculationServiceImpl() {
        HttpService httpService = new HttpServiceImpl();
    }

    public static void setHttpService(HttpService httpService) {
        TimeCalculationServiceImpl.httpService = httpService;
    }

    @Override
    public long calculateTime(Location origin, Location destination) {
        log.info(String.format("Calculation time between origin %s and destination %s", origin, destination));
        try {
            String url = buildUrl(origin, destination);
            byte[] response = httpService.executeUrl(url);
            System.out.println(response);
            JSONObject jo = (JSONObject) new JSONParser().parse(new String(response));
            log.info("JSON=" + jo.toString());
            JSONArray ja = (JSONArray) jo.get("rows");
            jo = (JSONObject) ja.get(0);
            ja = (JSONArray) jo.get("elements");
            jo = (JSONObject) ja.get(0);
            jo = (JSONObject) jo.get("duration");
            long res = (long) jo.get("value");
            log.info(String.format("Calculated time between origin %s and destination %s is %d", origin, destination, res));
            return res;

        } catch (ParseException e) {
            log.error("Error during parsing responce to JSON " + e.getMessage());
        }
        return -1;
    }

    public String buildUrl(Location origin, Location destination) {
        URIBuilder uriBuilder = null;
        try {
            uriBuilder = new URIBuilder(TIME_BETWEEN_TWO_POINT_URL);
            uriBuilder.addParameter(UNITS_PARAM, "imperial");
            uriBuilder.addParameter(ORIGINS_PARAM, origin.getLocationName());
            uriBuilder.addParameter(DESTINATIONS_PARAM, destination.getLocationName());
            uriBuilder.addParameter(KEY_PARAM, GoogleKey.MAPS_API_KEY);
        } catch (URISyntaxException e) {
            return "";
        }
        return uriBuilder.toString();
    }
}
