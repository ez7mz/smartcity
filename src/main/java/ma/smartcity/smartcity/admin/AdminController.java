package ma.smartcity.smartcity.admin;

import lombok.AllArgsConstructor;
import ma.smartcity.smartcity.appuser.AppUser;
import ma.smartcity.smartcity.appuser.AppUserService;
import ma.smartcity.smartcity.khouribgaDB.CityInfos;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class AdminController {

    private final AppUserService appUserService;
    private final AdminService adminService;

    @RequestMapping("/admin")
    public ModelAndView admin(Principal principal){
        ModelAndView modelAndView = new ModelAndView();
        String em = principal.getName();
        AppUser appUser = appUserService.findUserByEmail(em);
        modelAndView.addObject("appUser", appUser);
        modelAndView.setViewName("admin/admin");
        return modelAndView;
    }

    @RequestMapping("/itemSaved")
    public ModelAndView saveItem(@RequestParam("name") String name,
                                 @RequestParam("url") String url,
                                 @RequestParam("location") String location,
                                 @RequestParam("image") String image,
                                 @RequestParam("phone") String phone,
                                 @RequestParam("email") String email,
                                 @RequestParam("category") String category,
                                 @RequestParam("description") String description){
        CityInfos newItem = new CityInfos(name, location, url, email, phone, description, category, image);
        return adminService.saveItem(newItem);
    }
}
