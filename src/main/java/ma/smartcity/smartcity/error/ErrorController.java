package ma.smartcity.smartcity.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(Exception.class)
    public ModelAndView error(HttpServletRequest httpServletRequest, Exception ex){
        Object status = httpServletRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        int status_code = Integer.parseInt(status.toString());
        ModelAndView modelAndView = new ModelAndView();
        String message = ex.getMessage();
        if (status_code == 404){
            message = "Page not found!";
        }
        modelAndView.addObject("status", status_code);
        modelAndView.addObject("message", message);
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
