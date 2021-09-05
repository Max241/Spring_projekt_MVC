package pl.dmcs.brozga.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.brozga.model.AppUser;
import pl.dmcs.brozga.model.VisitHoursDTOSearch;
import pl.dmcs.brozga.service.VisitService;
import pl.dmcs.brozga.model.VisitHoursDTO;
import pl.dmcs.brozga.model.VisitHours;
import pl.dmcs.brozga.service.VisitHoursService;
import pl.dmcs.brozga.service.AppUserService;
import pl.dmcs.brozga.validator.AddVisitHoursValidator;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


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

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR')")
    @GetMapping("/add")
    public String addVisitHours(@RequestParam(value = "errorDate", required = false) String errorDate, Model model) {
        if (errorDate != null) {
            model.addAttribute("errorDate", "visitHours.error.overLapDate");
        }
        model.addAttribute("doctorsList", appUserService.getDoctorsList());
        model.addAttribute("addVisitHours", new VisitHoursDTO());
        return "addVisitHours";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR')")
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
                    visitHours.getStartDate().plusMinutes(visitHours.getVisitLength()))) {
                return "redirect:/visitHours/add?errorDate=true";
            }
            visitHoursService.addVisitHours(visitHours);
            return "redirect:/visitHours/list/1";
        }
        model.addAttribute("doctorsList", appUserService.getDoctorsList());
        return "addVisitHours";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR')")
    @GetMapping("/list/{id}")
    public String getVisitHoursList(@PathVariable Integer id, Principal principal, Model model) {
        Pageable pageable = new PageRequest(id - 1, 10);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        Page<VisitHours> visitHourPage;
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

    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR')")
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

    @GetMapping()
    public String getVisitHours(Model model,
                                @RequestParam(value = "notFound", required = false) String notFound) {
        if (notFound != null) {
            model.addAttribute("notFound", "visitHours.error.notFound");
        }
        model.addAttribute("doctorsList", appUserService.getDoctorsList());
        model.addAttribute("searchVisitHours", new VisitHoursDTOSearch());
        return "visitHours";
    }

    @PostMapping()
    public String searchVisitHours(@Valid @ModelAttribute("searchVisitHours") VisitHoursDTOSearch visitHours,
                                   Model model) {
        model.addAttribute("doctorsList", appUserService.getDoctorsList());
        model.addAttribute("searchVisitHours", visitHours);
        model.addAttribute("list", visitHoursService.getVisitHoursNotCancelledByDoctor(visitHours.getDoctorId()));
        model.addAttribute("visitHoursId", visitHours.getDoctorId());
        return "visitHours";
    }

    @GetMapping("/{id}")
    public String getVisitHour(@PathVariable Long id, @RequestParam(value = "notAvailable", required = false) String notAvailable, Model model) {
        if (notAvailable != null) {
            model.addAttribute("notAvailable", "visitHours.error.notAvailable");
        }
        VisitHours visitHours = visitHoursService.getSingleVisitHours(id);
        if (visitHours != null && visitHours.getEndDate().isAfter(LocalDateTime.now())) {
            List<List<String>> hours = new ArrayList<>();
            ArrayList<String> hoursArray = new ArrayList<>();
            hoursArray.add(visitHours.getStartDate().plusMinutes(visitHours.getVisitLength()).toString());
            hoursArray.add(visitHours.getEndDate().toString());
            hoursArray.add(String.valueOf(1));
            hoursArray.add(String.valueOf(visitService.isBookingAvailable(id, null)));
            hours.add(hoursArray);
            model.addAttribute("hourList", hours);
            model.addAttribute("visitHours", visitHours);
            return "selectedVisitHours";
        } else {
            return "redirect:/visitHours?notFound=true";
        }
    }
}
