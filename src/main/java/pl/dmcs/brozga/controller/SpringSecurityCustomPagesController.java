package pl.dmcs.brozga.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.dmcs.brozga.model.AppUser;
import pl.dmcs.brozga.service.AppUserService;

import javax.validation.Valid;

@Controller
public class SpringSecurityCustomPagesController {

    public AppUserService appUserService;

    @RequestMapping(value = "/login")
    public String customLogin(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout,
                              Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password");
        }

        if (logout != null) {
            model.addAttribute("msg", "You've been logged out successfully");
        }

        return "login";


    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register", "register", new AppUser());
    }

    @PostMapping("/register")
    public String registerPost(@Valid @ModelAttribute("register") AppUser user, BindingResult result) {
/*        userValidator.validate(user, result);
        System.out.println(result.getAllErrors());*/
        if (result.getErrorCount() == 0) {
            appUserService.addAppUser(user);
            return "redirect:/";
        }
        return "register";
    }

    @RequestMapping(value = "/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }

}


