package the.best.service.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import the.best.entity.Car;
import the.best.entity.Location;
import the.best.service.GetPlacePositionService;
import the.best.web.data.AjaxGetCarsResponse;
import the.best.web.data.LatLong;
import the.best.web.data.Order;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {
    @InjectMocks
    OrderServiceImpl instance;

    @Mock
    GetPlacePositionService getPlacePositionService;

    String locationId = "loc_id";

    @Before
    public void setUp() {
        instance = new OrderServiceImpl();
        instance.setGetPlacePositionService(getPlacePositionService);
    }

    @Test
    public void shouldReturnCarWhenItExistInList(){
        List<Order> list = new ArrayList<>();
        Order order = new Order();
        Car car = new Car();
        car.setId(1);
        order.setCar(car);
        list.add(order);
        assertEquals(car, instance.getCarById(list, 1).getCar());
    }

    @Test
    public void shouldReturnNullWhenItNotExistInList(){
        List<Order> list = new ArrayList<>();
        assertEquals(null, instance.getCarById(list, 1));
    }

    @Test
    public void shouldReturnListAjaxGetCarsResponseWhenInputList(){
        LatLong latLong = new LatLong(0,0);
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setCar(new Car());
        order.setCarLocation(new Location(locationId));
        orders.add(order);
        when(getPlacePositionService.getPosition(locationId)).thenReturn(latLong);
        List<AjaxGetCarsResponse> ajaxGetCarsResponses = instance.convertAjaxGetCarResponse(orders);
        assertEquals(latLong, ajaxGetCarsResponses.get(0).getLatLong());
    }

    @Test
    public void shouldReturnNullWhenInputNull(){
        LatLong latLong = new LatLong(0,0);
        List<AjaxGetCarsResponse> ajaxGetCarsResponses = instance.convertAjaxGetCarResponse(null);
        assertEquals(null, ajaxGetCarsResponses);
    }
}