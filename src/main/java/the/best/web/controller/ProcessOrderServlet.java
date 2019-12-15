package the.best.web.controller;

import lombok.extern.slf4j.Slf4j;
import the.best.dao.UserDAO;
import the.best.service.dao.*;
import the.best.utils.ParamAttrConstant;
import the.best.utils.UrlConstant;
import the.best.dao.OrderDAO;
import the.best.entity.Location;
import the.best.entity.User;
import the.best.web.data.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transaction;
import java.io.IOException;
import java.util.List;

@Slf4j
@WebServlet(UrlConstant.PROCESS_ORDER)
public class ProcessOrderServlet extends HttpServlet {
    OrderService orderService = new OrderServiceImpl();
    TransactionService transactionService = new TransactionServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Order> orders = (List<Order>) session.getAttribute(ParamAttrConstant.ORDERS);

        Integer car_id = new Integer(request.getParameter(ParamAttrConstant.CAR_ID));
        Order desirableOrder = orderService.getCarById(orders, car_id);
        desirableOrder.setDestination(new Location((String) session.getAttribute(ParamAttrConstant.DESTINATION)));
        desirableOrder.setOrigin(new Location((String) session.getAttribute(ParamAttrConstant.ORIGIN)));
        transactionService.processOrder(orderService.getCarById(orders, car_id), (User) session.getAttribute(ParamAttrConstant.USER));
        response.sendRedirect(UrlConstant.SUCCESS);

    }
}
