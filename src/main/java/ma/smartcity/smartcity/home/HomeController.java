package ma.smartcity.smartcity.home;

import lombok.AllArgsConstructor;
import ma.smartcity.smartcity.appuser.AppUser;
import ma.smartcity.smartcity.appuser.AppUserService;
import ma.smartcity.smartcity.khouribgaDB.CityInfosService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;


@Controller
@AllArgsConstructor
public class HomeController {

    private final AppUserService appUserService;
    private final CityInfosService cityInfosService;

    @RequestMapping(path = "/")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/dashboard")
    public ModelAndView dashboard(Principal principal){
        ModelAndView modelAndView = new ModelAndView();
        String em = principal.getName();
        AppUser appUser = appUserService.findUserByEmail(em);
        modelAndView.addObject("appUser", appUser);
        modelAndView.setViewName("home/dashboard/dashboard");
        return modelAndView;
    }

    @RequestMapping(path = "/forgot-password")
    public String forgotPassword(){
        return "registration/forgot-password";
    }

    @RequestMapping(path = "/lock-screen")
    public ModelAndView lockScreen(Principal principal){
        ModelAndView modelAndView = new ModelAndView();
        String em = principal.getName();
        AppUser appUser = appUserService.findUserByEmail(em);
        modelAndView.addObject("appUser", appUser);
        modelAndView.setViewName("registration/lock-screen");
        return modelAndView;
    }

    @RequestMapping(path = "/about")
    public String about(){
        return "home/about";
    }

    @RequestMapping("/coming-soon")
    public String comingSoon(){
        return "home/coming-soon";
    }

}
