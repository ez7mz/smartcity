package ma.smartcity.smartcity.home;

import lombok.AllArgsConstructor;
import ma.smartcity.smartcity.appuser.AppUser;
import ma.smartcity.smartcity.appuser.AppUserService;
import ma.smartcity.smartcity.khouribgaDB.CityInfos;
import ma.smartcity.smartcity.khouribgaDB.CityInfosService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {

    private final AppUserService appUserService;
    private final CityInfosService cityInfosService;

    @RequestMapping(path = "/student")
    public ModelAndView student(Principal principal){
        ModelAndView modelAndView = new ModelAndView();

        // get appUser currently loged in
        String em = principal.getName();
        AppUser appUser = appUserService.findUserByEmail(em);
        // add it to the ModelView
        modelAndView.addObject("appUser", appUser);

        // get City info in relation with students
        List<CityInfos> schools = cityInfosService.getCityInfoByCategory("School");
        List<CityInfos> libraries = cityInfosService.getCityInfoByCategory("Library");
        List<CityInfos> universities = cityInfosService.getCityInfoByCategory("University");
        List<CityInfos> institutions = cityInfosService.getCityInfoByCategory("Institution");

        // add it to the ModelView
        modelAndView.addObject("schools", schools);
        modelAndView.addObject("libraries", libraries);
        modelAndView.addObject("universities", universities);
        modelAndView.addObject("institutions", institutions);
        // set ViewName to identify wish page to render
        modelAndView.setViewName("home/dashboard/student");
        return modelAndView;
    }
}
