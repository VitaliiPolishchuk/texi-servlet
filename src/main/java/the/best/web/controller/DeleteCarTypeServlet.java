package the.best.web.controller;

import lombok.extern.slf4j.Slf4j;
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

    CarTypeDAO carTypeDAO = new CarTypeDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(ParamAttrConstant.TYPE_ID));
        carTypeDAO.delete(id);

        response.sendRedirect(UrlConstant.CAR_TYPES);
    }
}
