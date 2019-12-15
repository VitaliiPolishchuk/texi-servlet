package the.best.service;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import the.best.entity.Location;
import the.best.web.data.Order;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class NearestCarServiceImplTest {

    @InjectMocks
	NearestCarServiceImpl nearestCarService;

    @Mock
	TimeCalculationService timeCalculationService;

    Location destination;

    @Before
	public void setUp() {
		nearestCarService = new NearestCarServiceImpl();
		destination = new Location("d");
		nearestCarService.setTimeCalculationService(timeCalculationService);
	}

	@Test
	public void shouldReturnNullWhenOrdersIsEmpty() {
		assertEquals(null, nearestCarService.findNearest(null, new ArrayList<>()));
	}

	@Test
	public void shouldReturnFirstOneWhenOrders() {

    	Location carLocation1 = new Location("1");
    	Order order1 = new Order();
    	order1.setCarLocation(carLocation1);

		Location carLocation2 = new Location("2");
		Order order2 = new Order();
		order2.setCarLocation(carLocation2);

		Location carLocation1Up = new Location("place_id:1");
		Location carLocation2Up = new Location("place_id:2");

		when(timeCalculationService.calculateTime(carLocation1Up, destination)).thenReturn(1L);
		when(timeCalculationService.calculateTime(carLocation2Up, destination)).thenReturn(2L);

		List<Order> list = new ArrayList<>();
		list.add(order1);
		list.add(order2);

		String actualCarLocation = nearestCarService.findNearest(destination, list).getOrder().getCarLocation().getLocationName();

		assertEquals(carLocation1.getLocationName(), actualCarLocation);
	}
}
