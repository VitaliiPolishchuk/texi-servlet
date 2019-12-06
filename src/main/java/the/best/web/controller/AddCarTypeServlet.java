//package the.best.web.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import the.best.config.ParamAttrConfig;
//import the.best.config.UrlConfig;
//import the.best.config.ViewConfig;
//import the.best.entity.Discount;
//import the.best.entity.Location;
//import the.best.entity.User;
//import the.best.web.data.Order;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.List;
//
//@Slf4j
//@WebServlet(UrlConfig.CAR_TYPE_CHOOSE)
//public class AddCarTypeServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        String origin = (String)session.getAttribute(ParamAttrConfig.ORIGIN);
//        String destionation = (String)session.getAttribute(ParamAttrConfig.DESTINATION);
//        log.info("Origin is " + origin);
//        log.info("Destination is " + destionation);
//
//        List<Order> orders = generateNearestCarsService
//                .generate(new Location(origin), new Location(destionation));
//
//
//        session.setAttribute(ParamAttrConfig.ORDERS, orders);
//        12orderService.calculatePrice(orders, (User) session.getAttribute(ParamAttrConfig.USER), (Discount)session.getAttribute(ParamAttrConfig.DISCOUNT));
//
//
//        request.getRequestDispatcher(ViewConfig.CAR_TYPE_CHOOSE).forward(request, response);
//    }
//}
