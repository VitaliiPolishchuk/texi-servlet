package the.best.web.controller;

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

@WebServlet(UrlConstant.SUCCESS)
public class SuccessServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute(ParamAttrConstant.ORIGIN, null);
        session.setAttribute(ParamAttrConstant.DESTINATION, null);
        session.setAttribute(ParamAttrConstant.CAR, null);
        request.getRequestDispatcher(ViewConstant.SUCCESS).forward(request, response);
    }

}
