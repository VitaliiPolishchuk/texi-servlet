package the.best.web.controller;

import lombok.extern.slf4j.Slf4j;
import the.best.service.dao.UserService;
import the.best.service.dao.UserServiceImpl;
import the.best.utils.ParamAttrConstant;
import the.best.utils.UrlConstant;
import the.best.utils.ViewConstant;
import the.best.dao.UserDAO;
import the.best.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(UrlConstant.SING_UP)
public class SingUpServlet extends HttpServlet {

    UserService userService = new UserServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(ViewConstant.SING_UP).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter(ParamAttrConstant.FIRST_NAME);
        String lastName = request.getParameter(ParamAttrConstant.LAST_NAME);
        String email = request.getParameter(ParamAttrConstant.EMAIL);
        String emailPassword = request.getParameter(ParamAttrConstant.PASSWORD);
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmailPassword(emailPassword);
        if (userService.validate(user)) {
            userService.create(user);
            log.info("insert user " + user);
            request.getSession().setAttribute(ParamAttrConstant.USER, user);
            response.sendRedirect(UrlConstant.ORDER);
        } else {
            response.sendRedirect(UrlConstant.SING_UP);
        }
    }
}
