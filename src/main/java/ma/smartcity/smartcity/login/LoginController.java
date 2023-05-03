package ma.smartcity.smartcity.login;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class LoginController {

    @GetMapping("/login")
    public String login(Authentication authentication){
        if (authentication != null && authentication.isAuthenticated()) {
            // user is already logged in, redirect to dashboard
            return "redirect:/dashboard";
        }
        return "login";
    }

}
