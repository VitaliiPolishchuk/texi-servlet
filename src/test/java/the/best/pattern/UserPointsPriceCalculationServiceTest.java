package the.best.pattern;

import org.junit.Before;
import org.junit.Test;
import the.best.entity.User;
import the.best.pattern.wrapper.UserPointsPriceCalculationService;
import the.best.web.data.Order;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class UserPointsPriceCalculationServiceTest {
    UserPointsPriceCalculationService instance;

    @Before
    public void setUp() {
        instance = new UserPointsPriceCalculationService();
    }



    @Test
    public void shouldBe90DiscountPriceWhenWholePriceIs100AndIsUserPoints(){
        Order orderData = new Order();
        orderData.setWholePrice(100);
        orderData.setDiscountPrice(100);
        User user = new User();
        user.setPoints(100);

        instance.calculate(orderData, user, null);
        assertEquals(90.0, orderData.getDiscountPrice(), 0.01);
    }

    @Test
    public void shouldBe100WholePriceWhenWholePriceIs100AndIsUserPoints(){
        Order orderData = new Order();
        orderData.setWholePrice(100);
        orderData.setDiscountPrice(100);
        User user = new User();
        user.setPoints(100);

        instance.calculate(orderData, user, null);
        assertEquals(100.0, orderData.getWholePrice(), 0.01);
    }

    @Test
    public void shouldReturnTrueWhenWholePriceIs100AndIsUserPoints(){
        Order orderData = new Order();
        orderData.setWholePrice(100);
        orderData.setDiscountPrice(100);
        User user = new User();
        user.setPoints(100);

        instance.calculate(orderData, user, null);
        assertTrue(orderData.isUserPoints());
    }

    @Test
    public void shouldBe100DiscountPriceWhenWholePriceIs100(){
        Order orderData = new Order();
        orderData.setWholePrice(100);
        orderData.setDiscountPrice(100);
        User user = new User();
        user.setPoints(100);

        instance.calculate(orderData, user, null);
        assertEquals(90.0, orderData.getDiscountPrice(), 0.01);
    }

    @Test
    public void shouldBe100WholePriceWhenWholePriceIs100(){
        Order orderData = new Order();
        orderData.setWholePrice(100);
        orderData.setDiscountPrice(100);
        User user = new User();
        user.setPoints(100);

        instance.calculate(orderData, user, null);
        assertEquals(100.0, orderData.getWholePrice(), 0.01);
    }

    @Test
    public void shouldReturnTrueWhenWholePriceIs100(){
        Order orderData = new Order();
        orderData.setWholePrice(100);
        orderData.setDiscountPrice(100);
        User user = new User();
        user.setPoints(100);

        instance.calculate(orderData, user, null);
        assertTrue(orderData.isUserPoints());
    }

}