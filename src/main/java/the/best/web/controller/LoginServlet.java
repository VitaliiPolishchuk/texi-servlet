package the.best.web.controller;

import lombok.extern.slf4j.Slf4j;
import the.best.utils.UrlConstant;
import the.best.utils.ViewConstant;
import the.best.service.UserService;
import the.best.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
// front controller
@Slf4j
@WebServlet(UrlConstant.LOGIN)
public class LoginServlet  extends HttpServlet {

    UserService userService = new UserServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info(UrlConstant.CAR_TYPES);
        request.getRequestDispatcher(ViewConstant.LOGIN).forward(request, response);
    }
}
