package the.best.web.controller;

import lombok.extern.slf4j.Slf4j;
import the.best.entity.CarType;
import the.best.service.dao.CarService;
import the.best.service.dao.CarServiceImpl;
import the.best.service.dao.CarTypeService;
import the.best.service.dao.CarTypeServiceImpl;
import the.best.utils.ParamAttrConstant;
import the.best.utils.UrlConstant;
import the.best.utils.ViewConstant;
import the.best.dao.CarTypeDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Slf4j
@WebServlet(UrlConstant.CAR_TYPES)
public class CarTypesServlet extends HttpServlet {

    CarTypeService carTypeService = new CarTypeServiceImpl();
    CarService carService = new CarServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<CarType> types = carTypeService.getAll();

        request.setAttribute(ParamAttrConstant.CAR_TYPES, types);
        request.setAttribute(ParamAttrConstant.CARS, carService.getCars(types));
        request.getRequestDispatcher(ViewConstant.CAR_TYPES).forward(request, response);
    }
}
