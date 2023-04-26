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

        // get Count for <Milestones> Khouribga in numbers
        Long schools_count = cityInfosService.countByCategory("School");
        Long libraries_count = cityInfosService.countByCategory("Library");
        Long universities_count = cityInfosService.countByCategory("University");
        Long institutions_count = cityInfosService.countByCategory("Institution");


        // add infos to the ModelView
        modelAndView.addObject("schools", schools);
        modelAndView.addObject("libraries", libraries);
        modelAndView.addObject("universities", universities);
        modelAndView.addObject("institutions", institutions);

        // add counts to the ModelView
        modelAndView.addObject("schools_count", schools_count);
        modelAndView.addObject("libraries_count", libraries_count);
        modelAndView.addObject("universities_count", universities_count);
        modelAndView.addObject("institutions_count", institutions_count);


        // set ViewName to identify wish page to render
        modelAndView.setViewName("home/dashboard/student");
        return modelAndView;
    }
}
