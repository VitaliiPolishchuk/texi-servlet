package the.best.web.controller;

import lombok.extern.slf4j.Slf4j;
import the.best.entity.CarType;
import the.best.service.dao.CarTypeService;
import the.best.service.dao.CarTypeServiceImpl;
import the.best.utils.ParamAttrConstant;
import the.best.utils.UrlConstant;
import the.best.dao.CarTypeDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(UrlConstant.DELETE_CAR_TYPE)
public class DeleteCarTypeServlet extends HttpServlet {

    CarTypeService carTypeService = new CarTypeServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(ParamAttrConstant.TYPE_ID));
        CarType carType = new CarType();
        carType.setId(id);
        carTypeService.remove(carType);

        response.sendRedirect(UrlConstant.CAR_TYPES);
    }
}
