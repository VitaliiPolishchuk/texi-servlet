package the.best.web.controller;

import lombok.extern.slf4j.Slf4j;
import the.best.utils.ParamAttrConstant;
import the.best.utils.UrlConstant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(UrlConstant.LANGUAGE)
public class LanguageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String locale = request.getParameter(ParamAttrConstant.LOCALE);

        request.getSession().setAttribute(ParamAttrConstant.LOCALE, locale);
        log.info("language changed to " + locale);
        response.sendRedirect(UrlConstant.ORDER);
    }

}
