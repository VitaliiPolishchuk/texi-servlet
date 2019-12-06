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

    @Override
    public LatLong getPosition(String locationId) {
        URIBuilder uriBuilder = null;
        try {
            uriBuilder = new URIBuilder(REQUEST_URL);
            uriBuilder.addParameter(PLACE_ID_PARAM, locationId);
            uriBuilder.addParameter(FIELDS_PARAM, FIELDS);
            uriBuilder.addParameter(KEY_PARAM, GoogleKey.MAPS_API_KEY);

            log.info("url=" + uriBuilder.toString());
            byte[] response = httpService.executeUrl(uriBuilder.toString());

            JSONObject jo =  (JSONObject) new JSONParser().parse(new String(response));
            log.info("JSON=" + jo.toString());
            jo = (JSONObject) jo.get("result");
            jo = (JSONObject) jo.get("geometry");
            jo = (JSONObject) jo.get("location");

            LatLong latLong = new LatLong((double)jo.get("lat"), (double)jo.get("lng"));

            return latLong;
        } catch (URISyntaxException e){
            log.error("Error during building url " + e.getMessage());
        } catch (ParseException e) {
            log.error("Error during parsing responce to JSON " + e.getMessage());
        }

        return null;

    }
}
