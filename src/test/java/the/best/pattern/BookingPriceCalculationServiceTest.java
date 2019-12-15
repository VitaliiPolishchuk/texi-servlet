package the.best.pattern;

import org.junit.Before;
import org.junit.Test;
import the.best.entity.CarType;
import the.best.pattern.wrapper.BookingPriceCalculationService;
import the.best.web.data.Order;

import static org.junit.Assert.assertEquals;

public class BookingPriceCalculationServiceTest {

    BookingPriceCalculationService instance;


    @Before
    public void setUp() throws Exception {
        instance = new BookingPriceCalculationService();
    }

    @Test
    public void shouldBe70DiscountPriceWhenCarTypeIsBookingPriceIs70(){
        CarType carType = new CarType();
        carType.setPriceBooking(70.0);
        Order orderData = new Order();
        orderData.setCarType(carType);
        instance.calculate(orderData, null, null);
        assertEquals(70.0, orderData.getDiscountPrice(), 0.01);
    }

    @Test
    public void shouldBe70WholePriceWhenCarTypeIsBookingPriceIs70(){
        CarType carType = new CarType();
        carType.setPriceBooking(70.0);
        Order orderData = new Order();
        orderData.setCarType(carType);
        instance.calculate(orderData, null, null);
        assertEquals(70.0, orderData.getDiscountPrice(), 0.01);
    }
}