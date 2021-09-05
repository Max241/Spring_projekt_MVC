package pl.dmcs.brozga.service;

import pl.dmcs.brozga.model.AppUser;
import pl.dmcs.brozga.model.Visit;

import javax.servlet.http.HttpServletResponse;

public interface PdfService {
    public void generatePdf(AppUser appUser, HttpServletResponse response);

    public void generateBill(Visit visit, HttpServletResponse response);
}
