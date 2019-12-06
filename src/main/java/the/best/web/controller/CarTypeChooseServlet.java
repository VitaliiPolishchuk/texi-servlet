package the.best.web.controller;

import lombok.extern.slf4j.Slf4j;
import the.best.service.GenerateNearestCarsServiceImpl;
import the.best.utils.ParamAttrConstant;
import the.best.utils.UrlConstant;
import the.best.utils.ViewConstant;
import the.best.entity.Discount;
import the.best.entity.Location;
import the.best.web.data.Order;
import the.best.entity.User;
import the.best.service.OrderService;
import the.best.service.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Slf4j
@WebServlet(UrlConstant.CAR_TYPE_CHOOSE)
public class CarTypeChooseServlet extends HttpServlet {
    GenerateNearestCarsServiceImpl generateNearestCarsService = new GenerateNearestCarsServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String origin = (String)session.getAttribute(ParamAttrConstant.ORIGIN);
        String destionation = (String)session.getAttribute(ParamAttrConstant.DESTINATION);
        log.info("Origin is " + origin);
        log.info("Destination is " + destionation);

        List<Order> orders = generateNearestCarsService
                .generate(new Location(origin), new Location(destionation));


        session.setAttribute(ParamAttrConstant.ORDERS, orders);
        orderService.calculatePrice(orders, (User) session.getAttribute(ParamAttrConstant.USER), (Discount)session.getAttribute(ParamAttrConstant.DISCOUNT));


        request.getRequestDispatcher(ViewConstant.CAR_TYPE_CHOOSE).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter(ParamAttrConstant.CAR));

        response.sendRedirect(UrlConstant.SUCCESS);
    }
}
