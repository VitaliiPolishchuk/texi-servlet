package the.best.service.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import the.best.dao.UserDAO;
import the.best.entity.User;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl instance;

    @Mock
    UserDAO userDAO;

    @Before
    public void setUp() {
        instance = new UserServiceImpl();
        instance.setUserDAO(userDAO);
    }

    @Test
    public void shouldReturnFalseWhenEmailDoesntMatchPattern1(){
        User user = new User();
        user.setEmail("@gmail.com");
        assertFalse(instance.validate(user));
    }

    @Test
    public void shouldReturnFalseWhenEmailDoesntMatchPattern2(){
        User user = new User();
        user.setEmail("something.gmail@");
        assertFalse(instance.validate(user));
    }

    @Test
    public void shouldReturnFalseWhenEmailDoesntMatchPattern3(){
        User user = new User();
        user.setEmail("something.gmail.com");
        assertFalse(instance.validate(user));
    }

    @Test
    public void shouldReturnFalseWhenEmailAlreadyExist(){
        User user = new User();
        user.setEmail("something@gmail.com");
        when(userDAO.getById(user.getEmail())).thenReturn(new User());
        assertFalse(instance.validate(user));
    }

    @Test
    public void shouldReturnTrueWhenAllGood(){
        User user = new User();
        user.setEmail("something@gmail.com");
        when(userDAO.getById(user.getEmail())).thenReturn(null);
        assertTrue(instance.validate(user));
    }

}