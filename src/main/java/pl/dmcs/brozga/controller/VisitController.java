package pl.dmcs.brozga.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dmcs.brozga.model.AppUser;
import pl.dmcs.brozga.model.Visit;
import pl.dmcs.brozga.service.AppUserService;
import pl.dmcs.brozga.service.PdfService;
import pl.dmcs.brozga.service.VisitService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/visits")
public class VisitController {
    private AppUserService userService;
    private VisitService visitService;
    private PdfService pdfService;

    public VisitController(AppUserService userService, VisitService visitService, PdfService pdfService) {
        this.userService = userService;
        this.visitService = visitService;
        this.pdfService = pdfService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR')")
    @GetMapping("/list/{id}")
    public String getVisitHoursList(@PathVariable Integer id, Principal principal, Model model) {
        Pageable pageable = new PageRequest(id - 1, 10);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean iaAdmin = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        Page<Visit> visitPage;
        if (iaAdmin) {
            visitPage = visitService.getVisitsPageForAdmin(pageable);
        } else {
            AppUser princ = userService.findByEmail(principal.getName());
            visitPage = visitService.getVisitsPageForDoctor(princ.getId(), pageable);
        }
        model.addAttribute("visitList", visitPage.getContent());
        model.addAttribute("totalPages", visitPage.getTotalPages());
        model.addAttribute("currentPage", id);
        return "visitList";
    }

    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @GetMapping("/book/{id}}")
    public String book(@PathVariable Long id, Principal principal) {
        AppUser princ = userService.findByEmail(principal.getName());
        if (visitService.addVisit(princ, id)) {
            return "redirect:/visits/visits/1";
        }
        return "redirect:/visitHours/" + id + "?notAvailable=true";
    }

    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @GetMapping("/visits/{id}")
    public String getMyVisits(@PathVariable Integer id, Principal principal, Model model) {
        Pageable pageable = new PageRequest(id - 1, 10);
        AppUser princ = userService.findByEmail(principal.getName());
        Page<Visit> visitPage = visitService.getVisitsPageForUser(princ.getId(), pageable);
        model.addAttribute("visitList", visitPage.getContent());
        model.addAttribute("totalPages", visitPage.getTotalPages());
        model.addAttribute("currentPage", id);
        model.addAttribute("ldtNow", LocalDateTime.now());
        return "visits";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR', 'ROLE_PATIENT')")
    @PostMapping("/cancel/{id}")
    public String cancelVisit(@PathVariable Long id, Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        boolean isDoctor = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_DOCTOR"));
        AppUser princ = userService.findByEmail(principal.getName());
        if (isAdmin) {
            visitService.cancelVisitForAdmin(id);
        } else if (isDoctor) {
            visitService.cancelVisitForDoctor(princ.getId(), id);
        } else {
            visitService.cancelVisitForPatient(id, princ.getId());
            return "redirect:/visits/visits/1";
        }
        return "redirect:/visits/list/1";
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR')")
    @PostMapping("/approve/{id}")
    public String approveVisit(@PathVariable Long id, Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        if (isAdmin) {
            visitService.approveVisitForAdmin(id);
        } else {
            AppUser princ = userService.findByEmail(principal.getName());
            visitService.approveVisitForDoctor(id, princ.getId());
        }
        return "redirect:/visits/list/1";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DOCTOR', 'ROLE_PATIENT')")
    @GetMapping("/bill/{id}")
    public void getBill(@PathVariable Long id, Principal principal, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser princ = userService.findByEmail(principal.getName());
        Visit visit = visitService.getVisit(id);
        if (visit != null && visit.isApproved() &&
                (visit.getPatient().getId().equals(princ.getId()) || visit.getVisitHours().getDoctor().getId().equals(princ.getId()) || authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN")))) {
            pdfService.generateBill(visit, response);
        }
    }
}