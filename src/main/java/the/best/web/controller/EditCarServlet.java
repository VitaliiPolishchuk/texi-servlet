package the.best.web.controller;

import lombok.extern.slf4j.Slf4j;
import the.best.dao.CarDAO;
import the.best.service.dao.CarService;
import the.best.service.dao.CarServiceImpl;
import the.best.service.dao.CarTypeService;
import the.best.service.dao.CarTypeServiceImpl;
import the.best.utils.LocaleCodeConstant;
import the.best.utils.ParamAttrConstant;
import the.best.utils.UrlConstant;
import the.best.utils.ViewConstant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@Slf4j
@WebServlet(UrlConstant.EDIT_CAR)
public class EditCarServlet extends HttpServlet {

    CarService carService = new CarServiceImpl();
    CarTypeService carTypeService = new CarTypeServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String localeStr = (String) session.getAttribute(ParamAttrConstant.LOCALE);
        ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(localeStr));
        int id = Integer.parseInt(request.getParameter(ParamAttrConstant.CAR_ID));
        request.setAttribute(ParamAttrConstant.CAR_TYPES, carTypeService.getAll());
        request.setAttribute(ParamAttrConstant.CAR, carService.getById(id));
        log.info(carService.getById(id).toString());
        request.setAttribute(ParamAttrConstant.CAR_PAGE_TITLE, messages.getString(LocaleCodeConstant.EDIT_CAR_TITLE));

        request.getRequestDispatcher(ViewConstant.NEW_CAR).forward(request, response);
    }
}
