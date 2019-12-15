package the.best.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import the.best.entity.Location;
import the.best.request.HttpService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TimeCalculationServiceImplTest {

    @InjectMocks
    TimeCalculationServiceImpl instance;

    @Mock
    HttpService httpService;

    String ANSWER = "{\n" +
            "   \"destination_addresses\" : [ \"New York, NY, USA\" ],\n" +
            "   \"origin_addresses\" : [ \"Washington, DC, USA\" ],\n" +
            "   \"rows\" : [\n" +
            "      {\n" +
            "         \"elements\" : [\n" +
            "            {\n" +
            "               \"distance\" : {\n" +
            "                  \"text\" : \"225 mi\",\n" +
            "                  \"value\" : 361715\n" +
            "               },\n" +
            "               \"duration\" : {\n" +
            "                  \"text\" : \"3 hours 49 mins\",\n" +
            "                  \"value\" : 13725\n" +
            "               },\n" +
            "               \"status\" : \"OK\"\n" +
            "            }\n" +
            "         ]\n" +
            "      }\n" +
            "   ],\n" +
            "   \"status\" : \"OK\"\n" +
            "}";

    final String URL = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=1&destinations=2&key=AIzaSyDVlw7YdwmZo9aX-dHpIAXT0aLmJVPqRLU";

    Location location1;
    Location location2;

    @Before
    public void setUp() {
        instance = new TimeCalculationServiceImpl();
        instance.setHttpService(httpService);
        location2 = new Location("2");
        location1 = new Location("1");
    }

    @Test
    public void shouldBeEqualsWhenLocationIs1And2() {
        assertEquals(URL, instance.buildUrl(location1, location2));
    }

    @Test
    public void shouldBeEqualsWhenParseAnswer() {
        when(httpService.executeUrl(URL)).thenReturn(ANSWER.getBytes());
        assertEquals(13725, instance.calculateTime(location1, location2));
    }
}
