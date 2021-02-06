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
import pl.dmcs.brozga.service.AddressService;
import pl.dmcs.brozga.service.AppUserRoleService;
import pl.dmcs.brozga.service.AppUserService;
import pl.dmcs.brozga.validator.AppUserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class AppUserController {

    private AppUserService appUserService;
    private AddressService addressService;
    private AppUserRoleService appUserRoleService;


    private AppUserValidator appUserValidator = new AppUserValidator();

    @Autowired
    public AppUserController(AppUserService appUserService, AddressService addressService, AppUserRoleService appUserRoleService) {
        this.appUserService = appUserService;
        this.addressService = addressService;
        this.appUserRoleService = appUserRoleService;
    }

    @RequestMapping(value = "/appUsers")
    public String showAppUsers(Model model, HttpServletRequest request) {

        int appUserId = ServletRequestUtils.getIntParameter(request, "appUserId", -1);

        if (appUserId > 0) {
            AppUser appUser = appUserService.getAppUser(appUserId);
            appUser.setPassword("");
            appUser.setAddress(addressService.getAddress(appUserService.getAppUser(appUserId).getAddress().getId()));
            model.addAttribute("selectedAddress", appUserService.getAppUser(appUserId).getAddress().getId());
            model.addAttribute("appUser", appUser);
        } else
            model.addAttribute("appUser", new AppUser());

        model.addAttribute("appUserList", appUserService.listAppUser());
        model.addAttribute("appUserRoleList", appUserRoleService.listAppUserRole());
        model.addAttribute("addressesList", addressService.listAddress());

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
       model.addAttribute("addressesList", addressService.listAddress());
       model.addAttribute("appUserRoleList", appUserRoleService.listAppUserRole());
       return "appUser";

   }

    @RequestMapping("/delete/{appUserId}")
    public String deleteUser(@PathVariable("appUserId") Long appUserId) {
        //ToDo MakeVerification
        appUserService.removeAppUser(appUserId);
        return "redirect:/appUsers.html";
    }

}
