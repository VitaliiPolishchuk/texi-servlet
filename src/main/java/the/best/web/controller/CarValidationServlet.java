package the.best.web.controller;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import the.best.entity.Car;
import the.best.service.dao.CarService;
import the.best.service.dao.CarServiceImpl;
import the.best.utils.UrlConstant;
import the.best.web.data.AjaxSuccessResponse;
import the.best.web.data.CarForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebServlet(UrlConstant.CAR_VALIDATION)
public class CarValidationServlet extends HttpServlet {

    CarService carService = new CarServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String photoUrl = request.getParameter("photoUrl");
        String locationId = request.getParameter("locationId");
        int carTypeId = Integer.parseInt(request.getParameter("carTypeId"));
        boolean isAvailable = carService.intToBool(Integer.parseInt(request.getParameter("isAvailable")));
        CarForm carForm = new CarForm();
        carForm.setId(id);
        carForm.setName(name);
        carForm.setPhotoUrl(photoUrl);
        carForm.setLocationId(locationId);
        carForm.setCarType(carTypeId);
        carForm.setAvailable(isAvailable);
        log.info("carFrom = " + carForm);
        AjaxSuccessResponse ajaxResponse = new AjaxSuccessResponse();

        if (carService.validate(carForm)) {

            Car car = carService.getCar(carForm);
            if (carForm.getId() == 0) {
                carService.save(car);
                log.info("Successfully add new car");
            } else {
                carService.update(car);
                log.info("Successfully updated car");
            }

            ajaxResponse.setUrl(UrlConstant.CAR_TYPES);
            ajaxResponse.setSuccess(true);
        } else {
            ajaxResponse.setMessage("Car name is empty");
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(ajaxResponse));
    }
}
