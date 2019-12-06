package the.best.web.controller;

import lombok.extern.slf4j.Slf4j;
import the.best.utils.ParamAttrConstant;
import the.best.utils.UrlConstant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebServlet(UrlConstant.LOGOUT)
public class LogoutServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        log.info("user logout " + session.getAttribute(ParamAttrConstant.USER));
        session.setAttribute(ParamAttrConstant.USER, null);
        session.setAttribute(ParamAttrConstant.ORIGIN, null);
        session.setAttribute(ParamAttrConstant.DESTINATION, null);
        session.setAttribute(ParamAttrConstant.CAR, null);
        response.sendRedirect(UrlConstant.LOGIN);
    }
}
