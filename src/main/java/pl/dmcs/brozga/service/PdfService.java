package pl.dmcs.brozga.service;

import pl.dmcs.brozga.model.AppUser;

import javax.servlet.http.HttpServletResponse;

public interface PdfService {
    public void generatePdf(AppUser appUser, HttpServletResponse response);
}
