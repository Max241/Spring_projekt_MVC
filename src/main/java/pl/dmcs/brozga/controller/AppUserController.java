package pl.dmcs.brozga.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.dmcs.brozga.model.AppUser;
import pl.dmcs.brozga.model.AppUserDTO;
import pl.dmcs.brozga.repository.AppUserRoleRepo;
import pl.dmcs.brozga.service.AppUserRoleService;
import pl.dmcs.brozga.service.AppUserService;
import pl.dmcs.brozga.service.ReCaptchaService;
import pl.dmcs.brozga.validator.AppUserDTOValidator;
import pl.dmcs.brozga.validator.AppUserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class AppUserController {

    private AppUserService appUserService;
    private AppUserRoleService appUserRoleService;
    private AppUserValidator appUserValidator;
    private AppUserDTOValidator appUserDTOValidator;
    private ReCaptchaService reCaptchaService;
    private AppUserRoleRepo appUserRoleRepo;

    @Autowired
    public AppUserController(AppUserService appUserService, AppUserRoleService appUserRoleService, ReCaptchaService reCaptchaService, AppUserRoleRepo appUserRoleRepo) {
        this.appUserService = appUserService;
        this.appUserRoleService = appUserRoleService;
        this.reCaptchaService = reCaptchaService;
        this.appUserRoleRepo = appUserRoleRepo;
        this.appUserDTOValidator = new AppUserDTOValidator();
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


    @RequestMapping(value = "/addAppUser", method = RequestMethod.POST)
    public String addAppUser(@Valid @ModelAttribute("appUser") AppUser appUser, BindingResult result, Model model) { //appUser - JSP form, addAppUser - JSP form, transformation to java object from Spring

        System.out.println("Name" + appUser.getName() + "Surname" + appUser.getSurname()
                + "Phone Number" + appUser.getPhoneNumber() + "Email" + appUser.getEmail());

        appUserValidator.validate(appUser, result);

        if (result.getErrorCount() == 0) {
            if (appUser.getId() == 0)
                appUserService.addAppUser(appUser);
            else
                appUserService.editAppUser(appUser);

            return "redirect:appUsers.html";
        }

        model.addAttribute("appUserList", appUserService.listAppUser());
        model.addAttribute("appUserRoleList", appUserRoleService.listAppUserRole());
        return "appUser";

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/delete/{appUserId}")
    public String deleteUser(@PathVariable("appUserId") Long appUserId) {
        appUserService.removeAppUser(appUserId);
        return "redirect:/appUsers";
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register", "register", new AppUser());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(@Valid @ModelAttribute("register") AppUser appUser, BindingResult result, HttpServletRequest request) {
        appUserValidator.validate(appUser, result);
        System.out.println(result.getAllErrors());
        if (result.getErrorCount() == 0 && reCaptchaService.verify(request.getParameter("g-recaptcha-response"))) {
            appUserService.addAppUser(appUser);
            return "redirect:/";
        }
        return "register";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR', 'ROLE_PATIENT')")
    @GetMapping("/appUserEditProfile")
    public ModelAndView editProfile(Principal principal, @RequestParam(value = "success", required = false) String success, Model model) {
        if (success != null) {
            model.addAttribute("success", "AppUserEditProfile.success");
        }
        AppUser appUser = appUserService.findByEmail(principal.getName());
        return new ModelAndView("appUserEditProfile", "appUserEditProfile", appUser);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR', 'ROLE_PATIENT')")
    @PostMapping("/appUserEditProfile")
    public String editProfilePost(@Valid @ModelAttribute("appUserEditProfile") AppUserDTO editedAppUser, BindingResult result, Principal principal) {
        appUserDTOValidator.validate(editedAppUser, result);
        AppUser princ = appUserService.findByEmail(principal.getName());
        if (result.getErrorCount() == 0) {
            appUserService.editAppUserDetails(editedAppUser, princ);
            return "redirect:/appUserEditProfile?success=true";
        }
        return "appUserEditProfile";
    }

    @PreAuthorize("isAnonymous()")
    @RequestMapping("/activateAccount")
    public String activateAccount(@RequestParam(name = "token") String token) {
        appUserService.activateUser(token);
        return "login";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("appUsers/list/{id}")
    public String getUserList(Principal principal, @PathVariable Integer id, Model model) {
        AppUser user = appUserService.findByEmail(principal.getName());
        Pageable pageable = new PageRequest(id - 1, 10);
        Page<AppUser> userPage = appUserService.getUsersWhereIdIsNot(user.getId(), pageable);
        model.addAttribute("userList", userPage.getContent());
        return "userList";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("appUsers/get/{id}")
    public String getUser(@PathVariable Long id, @RequestParam(value = "success", required = false) String success, @RequestParam(value = "deleted", required = false) String deleted,
                          Model model, Principal principal) {
        if (success != null) {
            model.addAttribute("success", "editProfile.success");
        }
        if (deleted != null) {
            model.addAttribute("deleted", "editProfile.deleted");
        }
        AppUser princ = appUserService.findByEmail(principal.getName());
        AppUser editedUser = appUserService.getAppUser(id);
        if (princ.getId().equals(id) || editedUser == null) {
            return "redirect:/list/1";
        }
        model.addAttribute("editUser", editedUser);
        model.addAttribute("availableRoles", appUserRoleRepo.findAll());
        return "editUser";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("appUsers/get/{id}")
    public String updateUser(@PathVariable Long id, @Valid @ModelAttribute("editUser") AppUserDTO user, BindingResult result, Principal principal) {
        AppUser princ = appUserService.findByEmail(principal.getName());
        AppUser editedUser = appUserService.getAppUser(id);
        if (princ.getId().equals(id)) {
            return "redirect:/list/1";
        }
        appUserDTOValidator.validate(user, result);
        if (result.getErrorCount() == 0 && editedUser != null) {
            appUserService.editUserForAdmin(user, editedUser);
            return "redirect:/get/" + id + "?success=true";
        }
        return "redirect:/get/" + id;
    }

}
