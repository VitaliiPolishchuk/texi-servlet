package the.best.web.controller;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import the.best.service.dao.CarService;
import the.best.service.dao.CarServiceImpl;
import the.best.utils.ParamAttrConstant;
import the.best.utils.UrlConstant;
import the.best.dao.CarDAO;
import the.best.service.dao.OrderService;
import the.best.service.dao.OrderServiceImpl;
import the.best.web.data.AjaxGetCarsResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@WebServlet(UrlConstant.GET_CARS)
public class GetCarsServlet extends HttpServlet {

    OrderService orderService = new OrderServiceImpl();
    CarService carService = new CarServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().write("Hello");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int typeId = Integer.parseInt(request.getParameter(ParamAttrConstant.TYPE_ID));
        log.debug("car type =" + typeId);
        response.setContentType("application/json");

        List<AjaxGetCarsResponse> ajaxResponse = orderService.convertAjaxGetCarResponse(carService.convert(carService.getAllWithLocationIdActiveByTypeId(typeId)));
        response.getWriter().write(new Gson().toJson(ajaxResponse));
    }
}
