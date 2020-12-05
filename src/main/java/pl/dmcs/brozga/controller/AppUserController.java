package pl.dmcs.brozga.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.dmcs.brozga.model.AppUser;

@Controller
public class AppUserController {

    @RequestMapping(value ="appUsers")
    public ModelAndView showAppUsers() {

        return new ModelAndView("appUser", "appUser", new AppUser()); // JPS file, model name, new object
        }

        @RequestMapping(value="/addAppUser",method = RequestMethod.POST)
        public String addAppUser(@ModelAttribute("appUser") AppUser appUser){ //appUser - JSP form, addAppUser - JSP form, transformation to java object from Spring

        System.out.println("Name" + appUser.getName() + "Surname" + appUser.getSurname()
                +"Phone Number" + appUser.getPhoneNumber() + "Email" + appUser.getEmail());

        return "redirect:appUser.html";


    }

}
