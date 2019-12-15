package the.best.pattern;

import org.junit.Before;
import org.junit.Test;
import the.best.entity.CarType;
import the.best.pattern.wrapper.KmPriceCalculationService;
import the.best.web.data.Order;

import static org.junit.Assert.assertEquals;

public class KmPriceCalculationServiceTest {

    KmPriceCalculationService instance;

    @Before
    public void setUp() {
        instance = new KmPriceCalculationService();
    }

    @Test
    public void shouldBe10DiscountPriceWhenTimeIs600(){
        CarType carType = new CarType();
        Order orderData = new Order();
        orderData.setTimeToReachDestination(700);
        orderData.setTimeToReachOrigin(100);

        instance.calculate(orderData, null, null);
        assertEquals(10.0, orderData.getDiscountPrice(), 0.01);
    }

    @Test
    public void shouldBe10WholePriceWhenTimeIs600(){
        CarType carType = new CarType();
        Order orderData = new Order();
        orderData.setTimeToReachDestination(700);
        orderData.setTimeToReachOrigin(100);

        instance.calculate(orderData, null, null);
        assertEquals(10.0, orderData.getWholePrice(), 0.01);
    }
}