package pl.dmcs.brozga.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.dmcs.brozga.model.AppUser;
import pl.dmcs.brozga.model.AppUserDTO;
import pl.dmcs.brozga.service.AppUserRoleService;
import pl.dmcs.brozga.service.AppUserService;
import pl.dmcs.brozga.validator.AppUserEditDataValidator;
import pl.dmcs.brozga.validator.AppUserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class AppUserController {

    private AppUserService appUserService;
    private AppUserRoleService appUserRoleService;
    private AppUserValidator appUserValidator;
    private AppUserEditDataValidator appUserEditDataValidator;

    @Autowired
    public AppUserController(AppUserService appUserService, AppUserRoleService appUserRoleService) {
        this.appUserService = appUserService;
        this.appUserRoleService = appUserRoleService;
        this.appUserValidator = new AppUserValidator(appUserService);
    }

    @RequestMapping(value = "/appUsers")
    public String showAppUsers(Model model, HttpServletRequest request) {

        int appUserId = ServletRequestUtils.getIntParameter(request, "appUserId", -1);

        if (appUserId > 0) {
            AppUser appUser = appUserService.getAppUser(appUserId);
            appUser.setPassword("");
            model.addAttribute("appUser", appUser);
        } else
            model.addAttribute("appUser", new AppUser());

        model.addAttribute("appUserList", appUserService.listAppUser());
        model.addAttribute("appUserRoleList", appUserRoleService.listAppUserRole());

        return "appUser";
    }


   @RequestMapping(value="/addAppUser",method = RequestMethod.POST)
   public String addAppUser(@Valid @ModelAttribute("appUser") AppUser appUser, BindingResult result, Model model){ //appUser - JSP form, addAppUser - JSP form, transformation to java object from Spring

        System.out.println("Name" + appUser.getName() + "Surname" + appUser.getSurname()
                +"Phone Number" + appUser.getPhoneNumber() + "Email" + appUser.getEmail());

        appUserValidator.validate(appUser,result);

       if (result.getErrorCount() == 0) {
           if (appUser.getId() == 0)
               appUserService.addAppUser(appUser);
           else
               appUserService.editAppUser(appUser);

           return "redirect:appUsers.html";
       }

       model.addAttribute("appUserList", appUserService.listAppUser());
       //model.addAttribute("addressesList", addressService.listAddress());
       model.addAttribute("appUserRoleList", appUserRoleService.listAppUserRole());
       return "appUser";

   }

    @RequestMapping("/delete/{appUserId}")
    public String deleteUser(@PathVariable("appUserId") Long appUserId) {
        //ToDo MakeVerification
        appUserService.removeAppUser(appUserId);
        return "redirect:/appUsers.html";
    }


    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register", "register", new AppUser());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(@Valid @ModelAttribute("register") AppUser appUser, BindingResult result) {
        appUserValidator.validate(appUser, result);
        System.out.println(result.getAllErrors());
        if (result.getErrorCount() == 0) {
            appUserService.addAppUser(appUser);
            return "redirect:/";
        }
        return "register";
    }

    @GetMapping("/appUserEditProfile")
    public ModelAndView editProfile(Principal principal, @RequestParam(value = "success", required = false) String success, Model model) {
        if (success != null) {
            model.addAttribute("success", "AppUserEditProfile.success");
        }
        AppUser appUser = appUserService.findByEmail(principal.getName());
        return new ModelAndView("appUserEditProfile", "appUserEditProfile", appUser);
    }

    @PostMapping("/appUserEditProfile")
    public String editProfilePost(@Valid @ModelAttribute("appUserEditProfile") AppUserDTO editedAppUser, BindingResult result, Principal principal) {
        //appUserEditDataValidator.validate(editedAppUser, result);
        AppUser appUser = appUserService.findByEmail(principal.getName());
        if (result.getErrorCount() == 0) {
            appUser.setName(editedAppUser.getName());
            appUserService.editAppUserDetails(appUser, editedAppUser);
            return "redirect:/";
        }
        return "appUserEditProfile";
    }

    @PreAuthorize("isAnonymous()")
    @RequestMapping("/activateAccount")
    public String activateAccount(@RequestParam(name = "token") String token) {
        appUserService.activateUser(token);
        return "login";
    }

}
