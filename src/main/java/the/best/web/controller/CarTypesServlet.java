package the.best.web.controller;

import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@WebServlet(UrlConstant.CAR_TYPES)
public class CarTypesServlet extends HttpServlet {

    CarTypeDAO carTypeDAO = new CarTypeDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        log.info("success");
        session.setAttribute(ParamAttrConstant.CAR_TYPES, carTypeDAO.getAll());
        request.getRequestDispatcher(ViewConstant.CAR_TYPES).forward(request, response);
    }
}
