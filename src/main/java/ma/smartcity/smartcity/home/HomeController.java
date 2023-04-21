package ma.smartcity.smartcity.home;

import lombok.AllArgsConstructor;
import ma.smartcity.smartcity.appuser.AppUser;
import ma.smartcity.smartcity.appuser.AppUserService;
import ma.smartcity.smartcity.khouribgaDB.CityInfos;
import ma.smartcity.smartcity.khouribgaDB.CityInfosService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;


@Controller
@AllArgsConstructor
public class HomeController {

    private final AppUserService appUserService;
    private final CityInfosService cityInfosService;

    @RequestMapping(path = "/")
    public ModelAndView index(){
        List<CityInfos> infos = cityInfosService.getAllCityInfos();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("infos", infos);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/home")
    public String home(Principal principal, Model model){

        String em = principal.getName();
        AppUser appUser = appUserService.findUserByEmail(em);

        model.addAttribute("first_name", appUser.getFirstName());
        model.addAttribute("last_name", appUser.getLastName());
        model.addAttribute("email", appUser.getEmail());
        model.addAttribute("password", appUser.getPassword());
        return "home";
    }

}
