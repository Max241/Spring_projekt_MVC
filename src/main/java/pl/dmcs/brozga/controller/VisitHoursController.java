package pl.dmcs.brozga.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.brozga.model.AppUser;
import pl.dmcs.brozga.service.VisitService;
import pl.dmcs.brozga.model.VisitHoursDTO;
import pl.dmcs.brozga.model.VisitHours;
import pl.dmcs.brozga.service.VisitHoursService;
import pl.dmcs.brozga.service.AppUserService;
import pl.dmcs.brozga.validator.AddVisitHoursValidator;

import javax.validation.Valid;
import java.security.Principal;


@Controller
@RequestMapping("/visitHours")
public class VisitHoursController {
    private VisitHoursService visitHoursService;
    private AppUserService appUserService;
    private VisitService visitService;
    private AddVisitHoursValidator addVisitHoursValidator;

    public VisitHoursController(VisitHoursService visitHoursService, AppUserService appUserService, VisitService visitService) {
        this.visitHoursService = visitHoursService;
        this.appUserService = appUserService;
        this.visitService = visitService;
        this.addVisitHoursValidator = new AddVisitHoursValidator();
    }


    @GetMapping("/add")
    public String addVisitHours(@RequestParam(value = "errorDate", required = false) String errorDate, Model model) {
        if (errorDate != null) {
            model.addAttribute("errorDate", "visitHours.error.overLapDate");
        }
        model.addAttribute("doctorsList", appUserService.getDoctorsList());
        model.addAttribute("addVisitHours", new VisitHoursDTO());
        return "addVisitHours";
    }

    @PostMapping("/add")
    public String addVisitHoursPost(@Valid @ModelAttribute("addVisitHours") VisitHoursDTO visitHours, BindingResult result, Principal principal, Model model) {
        addVisitHoursValidator.validate(visitHours, result);
        if (result.getErrorCount() == 0) {
            if (visitHours.getDoctorId() == null) {
                visitHours.setDoctorId(appUserService.findByEmail(principal.getName()).getId());
            }
            if (visitHoursService.hasDoctorVisitingHours(
                    visitHours.getDoctorId(),
                    visitHours.getStartDate(),
                    visitHours.getStartDate().plusMinutes(visitHours.getVisitLength() * visitHours.getVisitsCount()))) {
                return "redirect:/visitHours/add?errorDate=true";
            }
            visitHoursService.addVisitHours(visitHours);
            return "redirect:/visitHours/list/1";
        }
        model.addAttribute("doctorsList", appUserService.getDoctorsList());
        return "addVisitHours";
    }

    @GetMapping("/list/{id}")
    public String getVisitHoursList(@PathVariable Integer id, Principal principal, Model model) {
        Pageable pageable = new PageRequest(id - 1, 10);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        Page<VisitHours> visitHourPage = null;
        if (isAdmin) {
            visitHourPage = visitHoursService.getVisitHoursNotCancelledForAdmin(pageable);
        } else {
            AppUser princ = appUserService.findByEmail(principal.getName());
            visitHourPage = visitHoursService.getVisitHoursNotCancelledForDoctor(princ.getId(), pageable);
        }
        model.addAttribute("visitHoursList", visitHourPage.getContent());
        model.addAttribute("totalPages", visitHourPage.getTotalPages());
        model.addAttribute("currentPage", id);
        return "visitHoursList";
    }

    @PostMapping("/cancel/{id}")
    public String cancelVisitHoursPost(@PathVariable Long id, Principal principal) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        if (isAdmin) {
            visitHoursService.cancelVisitHoursForAdmin(id);
        } else {
            AppUser princ = appUserService.findByEmail(principal.getName());
            visitHoursService.cancelVisitHoursForDoctor(princ.getId(), id);
        }
        return "redirect:/visitHours/list/1";
    }
}
