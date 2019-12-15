package the.best.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import the.best.entity.Location;
import the.best.request.HttpService;
import the.best.web.data.LatLong;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class GetPlacePositionServiceImplTest {
    @InjectMocks
    GetPlacePositionServiceImpl instance;

    @Mock
    HttpService httpService;

    final String locationId = "ChIJ5a8UZZfP1EAR-InTEQYB-RI";

    final String URL = "https://maps.googleapis.com/maps/api/place/details/json?ChIJQ3wCj8PI1EARtCRUwFbxYYQ&fields=name%2Cgeometry%2Crating%2Cformatted_phone_number&place_id=ChIJ5a8UZZfP1EAR-InTEQYB-RI&fields=geometry&key=AIzaSyDVlw7YdwmZo9aX-dHpIAXT0aLmJVPqRLU";
    final String ANSWER = "{\n" +
            "   \"html_attributions\" : [],\n" +
            "   \"result\" : {\n" +
            "      \"geometry\" : {\n" +
            "         \"location\" : {\n" +
            "            \"lat\" : 50.4440605,\n" +
            "            \"lng\" : 30.57601269999999\n" +
            "         },\n" +
            "         \"viewport\" : {\n" +
            "            \"northeast\" : {\n" +
            "               \"lat\" : 50.4452097802915,\n" +
            "               \"lng\" : 30.5774962802915\n" +
            "            },\n" +
            "            \"southwest\" : {\n" +
            "               \"lat\" : 50.44251181970851,\n" +
            "               \"lng\" : 30.5747983197085\n" +
            "            }\n" +
            "         }\n" +
            "      },\n" +
            "      \"name\" : \"Гідропарк\",\n" +
            "      \"rating\" : 4.2\n" +
            "   },\n" +
            "   \"status\" : \"OK\"\n" +
            "}";

    @Before
    public void setUp() {
        instance = new GetPlacePositionServiceImpl();
        instance.setHttpService(httpService);
    }

    @Test
    public void shouldBeEqualsWhenBuildingUrl(){
        assertEquals(URL, instance.buildUrl(locationId));
    }

    @Test
    public void shouldBeEqualsWhenParsingAnswer(){
        when(httpService.executeUrl(URL)).thenReturn(ANSWER.getBytes());
        assertEquals(new LatLong(50.4440605, 30.57601269999999), instance.getPosition(locationId));
    }

}