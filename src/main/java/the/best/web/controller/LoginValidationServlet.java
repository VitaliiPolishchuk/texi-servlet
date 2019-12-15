package the.best.web.controller;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import the.best.utils.LocaleCodeConstant;
import the.best.utils.ParamAttrConstant;
import the.best.utils.UrlConstant;
import the.best.entity.User;
import the.best.service.dao.UserService;
import the.best.service.dao.UserServiceImpl;
import the.best.web.data.AjaxSuccessResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@Slf4j
@WebServlet(UrlConstant.LOGIN_VALIDATION)
public class LoginValidationServlet extends HttpServlet {

    UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        log.debug(String.format("email = %s, password = %s", email, password));
        User user = new User();
        user.setEmail(email);
        user.setEmailPassword(password);
        user = userService.doLogin(user);

        AjaxSuccessResponse ajaxResponse = new AjaxSuccessResponse();
        log.debug("user=" + user);
        if (user != null) {
            log.info("Login success");
            session.setAttribute("user", user);
            ajaxResponse.setUrl("/order");
            ajaxResponse.setSuccess(true);
        } else {
            String localeStr = (String) session.getAttribute(ParamAttrConstant.LOCALE);
            ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(localeStr));
            ajaxResponse.setMessage(messages.getString(LocaleCodeConstant.ERROR_LOGIN_MESSAGE));
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(ajaxResponse));
    }
}
