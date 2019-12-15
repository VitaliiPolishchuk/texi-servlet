package the.best.pattern;

import org.junit.Before;
import org.junit.Test;
import the.best.entity.CarType;
import the.best.entity.Discount;
import the.best.pattern.wrapper.DiscountPriceCalculationService;
import the.best.web.data.Order;

import static org.junit.Assert.assertEquals;

public class DiscountPriceCalculationServiceTest {
    DiscountPriceCalculationService instance;


    @Before
    public void setUp() throws Exception {
        instance = new DiscountPriceCalculationService();
    }

    @Test
    public void shouldBe45DiscountPriceWhenCarTypeIsWholePriceis100(){
        CarType carType = new CarType();
        Order orderData = new Order();
        orderData.setCarType(carType);
        orderData.setWholePrice(100.0);
        orderData.setDiscountPrice(100.0);

        Discount discount = new Discount();
        discount.setPercent(0.55);

        instance.calculate(orderData, null, discount);
        assertEquals(45.0, orderData.getDiscountPrice(), 0.01);
    }

    @Test
    public void shouldBe100WholePriceWhenCarTypeIsWholePriceis100(){
        CarType carType = new CarType();
        Order orderData = new Order();
        orderData.setCarType(carType);
        orderData.setWholePrice(100.0);
        orderData.setDiscountPrice(100.0);

        Discount discount = new Discount();
        discount.setPercent(0.55);

        instance.calculate(orderData, null, discount);
        assertEquals(100.0, orderData.getWholePrice(), 0.01);
    }
}