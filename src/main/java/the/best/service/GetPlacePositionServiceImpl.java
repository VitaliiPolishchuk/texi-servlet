package the.best.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import the.best.utils.GoogleKey;
import the.best.request.HttpService;
import the.best.request.HttpServiceImpl;
import the.best.web.data.LatLong;

import java.net.URISyntaxException;
// service packege
@Slf4j
public class GetPlacePositionServiceImpl implements GetPlacePositionService {

    HttpService httpService = HttpServiceImpl.getInstance();

    private static final String REQUEST_URL = "https://maps.googleapis.com/maps/api/place/details/json?ChIJQ3wCj8PI1EARtCRUwFbxYYQ&fields=name,geometry,rating,formatted_phone_number";
    private static final String PLACE_ID_PARAM = "place_id";
    private static final String KEY_PARAM = "key";
    private static final String FIELDS_PARAM = "fields";
    private static final String FIELDS = "geometry";

    public void setHttpService(HttpService httpService) {
        this.httpService = httpService;
    }

    @Override
    public LatLong getPosition(String locationId) {
        log.info("Getting LatLong by location id " + locationId);
        try {
            String url = buildUrl(locationId);
            byte[] response = httpService.executeUrl(url);

            JSONObject jo =  (JSONObject) new JSONParser().parse(new String(response));
            log.info("JSON=" + jo.toString());
            jo = (JSONObject) jo.get("result");
            jo = (JSONObject) jo.get("geometry");
            jo = (JSONObject) jo.get("location");

            LatLong latLong = new LatLong((double)jo.get("lat"), (double)jo.get("lng"));
            log.info("Gotten LatLong by location id " + locationId);
            return latLong;
        }  catch (ParseException e) {
            log.error("Error during parsing responce to JSON " + e.getMessage());
            return null;
        }
    }

    public String buildUrl(String locationId) {
        URIBuilder uriBuilder = null;
        try {
            uriBuilder = new URIBuilder(REQUEST_URL);
            uriBuilder.addParameter(PLACE_ID_PARAM, locationId);
            uriBuilder.addParameter(FIELDS_PARAM, FIELDS);
            uriBuilder.addParameter(KEY_PARAM, GoogleKey.MAPS_API_KEY);
        } catch (URISyntaxException e) {
            log.error("Error during building url " + e.getMessage());
            return "";
        }

        return uriBuilder.toString();
    }
}
