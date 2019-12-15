package the.best.web.controller;

import lombok.extern.slf4j.Slf4j;
import the.best.service.dao.DiscountService;
import the.best.service.dao.DiscountServiceImpl;
import the.best.utils.ParamAttrConstant;
import the.best.utils.UrlConstant;
import the.best.utils.ViewConstant;
import the.best.dao.DiscountDAO;
import the.best.entity.Discount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@WebServlet(UrlConstant.ORDER)
public class OrderServlet extends HttpServlet {

    DiscountService discountService = new DiscountServiceImpl();

    private static final long serialVersionUID = 1L; //serialization

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(ViewConstant.ORDER).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        log.info("do post");
        HttpSession session = request.getSession();

        session.setAttribute(ParamAttrConstant.ORIGIN, request.getParameter(ParamAttrConstant.ORIGIN));
        session.setAttribute(ParamAttrConstant.DESTINATION, request.getParameter(ParamAttrConstant.DESTINATION));
        session.setAttribute(ParamAttrConstant.DISCOUNT, request.getParameter(ParamAttrConstant.DISCOUNT));
        Discount discount = discountService.getById(request.getParameter(ParamAttrConstant.DISCOUNT));

        session.setAttribute(ParamAttrConstant.DISCOUNT, discount);

        log.debug("Origin is " + session.getAttribute(ParamAttrConstant.ORIGIN));
        log.debug("Destination is " + session.getAttribute(ParamAttrConstant.DESTINATION));
        log.debug("Discount id " + discount);
        log.info("done post");
        response.sendRedirect(UrlConstant.CAR_TYPE_CHOOSE);
    }


}