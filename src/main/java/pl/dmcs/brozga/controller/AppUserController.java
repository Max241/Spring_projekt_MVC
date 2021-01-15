package pl.dmcs.brozga.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.dmcs.brozga.model.AppUser;
import pl.dmcs.brozga.service.AppUserService;
import pl.dmcs.brozga.validator.AppUserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class AppUserController {

    private AppUserService appUserService;

    private AppUserValidator appUserValidator = new AppUserValidator();

    @Autowired
    public AppUserController(AppUserService appUserService)
    {this.appUserService = appUserService;}

    @RequestMapping(value ="/appUsers")
    public String showAppUsers(Model model, HttpServletRequest request) {

        int appUserId = ServletRequestUtils.getIntParameter(request, "appUserId", -1 );

        if(appUserId > 0)
            model.addAttribute("appUser", appUserService.getAppUser(appUserId));
        else
            model.addAttribute("appUser",new AppUser());

        model.addAttribute("appUserList",appUserService.listAppUser());

        return "appUser";

      //  return new ModelAndView("appUser", "appUser", new AppUser()); // JPS file, model name, new object
        }




   @RequestMapping(value="/addAppUser",method = RequestMethod.POST)
   public String addAppUser(@Valid @ModelAttribute("appUser") AppUser appUser, BindingResult result, Model model){ //appUser - JSP form, addAppUser - JSP form, transformation to java object from Spring

        System.out.println("Name" + appUser.getName() + "Surname" + appUser.getSurname()
                +"Phone Number" + appUser.getPhoneNumber() + "Email" + appUser.getEmail());

        appUserValidator.validate(appUser,result);

        if(result.getErrorCount() == 0){
            if(appUser.getId()==0)
                appUserService.addAppUser(appUser);
            else
                appUserService.editAppUser(appUser);

            return "redirect:appUsers.html";
        }

        model.addAttribute("appUserList",appUserService.listAppUser());
        return "appUser";

    }

    @RequestMapping("/delete/{appUserId}")
    public String deleteUser(@PathVariable("appUserId") Long appUserId) {
        //ToDo MakeVerification
        appUserService.removeAppUser(appUserId);
        return "redirect:/appUsers.html";
    }

}
