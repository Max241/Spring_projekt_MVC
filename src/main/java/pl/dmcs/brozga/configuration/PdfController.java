package pl.dmcs.brozga.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.dmcs.brozga.service.AppUserService;
import pl.dmcs.brozga.service.PdfService;

import javax.servlet.http.HttpServletResponse;

@Controller
public class PdfController {

    private PdfService pdfService;
    private AppUserService appUserService;

    @Autowired
    public PdfController(PdfService pdfService, AppUserService appUserService) {
        this.pdfService = pdfService;
        this.appUserService = appUserService;
    }

    @RequestMapping(value = "/generatePdf-{appUserId}", method = RequestMethod.GET)
    public void generatePdf(@PathVariable Integer appUserId, HttpServletResponse response) {
        pdfService.generatePdf(appUserService.getAppUser(appUserId), response);
    }
}
