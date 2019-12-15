package the.best.service.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import the.best.dao.CarDAO;
import the.best.dao.CarTypeDAO;
import the.best.entity.Car;
import the.best.entity.CarType;
import the.best.web.data.CarForm;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceImplTest {
    @InjectMocks
    CarServiceImpl instance;

    @Mock
    CarTypeDAO carTypeDAO;

    @Before
    public void setUp() {
        instance = new CarServiceImpl();
        instance.setCarTypeDAO(carTypeDAO);
        when(carTypeDAO.getById(2)).thenReturn(new CarType());
    }

    @Test
    public void shouldReturnFalseWhenNameIsEmpty() {

        CarForm carForm = new CarForm();
        carForm.setName("");
        assertFalse(instance.validate(carForm));
    }

    @Test
    public void shouldReturnFalseWhenEmailExist() {

        CarForm carForm = new CarForm();
        carForm.setName("23");
        carForm.setId(2);

        assertFalse(instance.validate(carForm));
    }

    @Test
    public void shouldReturnFalseWhenLocationIdEmpty() {

        CarForm carForm = new CarForm();
        carForm.setName("23");
        carForm.setId(2);
        carForm.setLocationId("");
        assertFalse(instance.validate(carForm));
    }

    @Test
    public void shouldReturnTrueWhenLocationAllGood() {

        CarForm carForm = new CarForm();
        carForm.setName("name");
        carForm.setCarType(2);
        carForm.setLocationId("loc_id");
        assertTrue(instance.validate(carForm));
    }

    @Test
    public void shouldReturnFalseWhenCarFormNull() {
        assertFalse(instance.validate(null));
    }
}