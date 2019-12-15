package the.best.web.controller;

import lombok.extern.slf4j.Slf4j;
import the.best.dao.CarDAO;
import the.best.entity.Car;
import the.best.service.dao.CarService;
import the.best.service.dao.CarServiceImpl;
import the.best.utils.ParamAttrConstant;
import the.best.utils.UrlConstant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(UrlConstant.DELETE_CAR)
public class DeleteCarServlet extends HttpServlet {

    CarService carService = new CarServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(ParamAttrConstant.CAR_ID));
        Car car = new Car();
        car.setId(id);
        carService.remove(car);
        response.sendRedirect(UrlConstant.CAR_TYPES);
    }
}
