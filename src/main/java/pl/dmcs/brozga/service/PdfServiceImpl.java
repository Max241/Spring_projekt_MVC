package pl.dmcs.brozga.service;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import pl.dmcs.brozga.model.AppUser;
import pl.dmcs.brozga.model.Visit;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class PdfServiceImpl implements PdfService {

    public void generatePdf(AppUser appUser, HttpServletResponse response) {
        try {
            OutputStream o = response.getOutputStream();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=" + appUser.getLogin() + ".pdf");
            Document pdf = new Document();
            PdfWriter.getInstance(pdf, o);
            pdf.open();
            pdf.add(new Paragraph("Pdf example - KAPJ"));
            pdf.add(new Paragraph(Chunk.NEWLINE));
            PdfPTable table = new PdfPTable(2);
            table.addCell("FirsName");
            table.addCell(appUser.getName());
            table.addCell("LastName");
            table.addCell(appUser.getSurname());
            table.addCell("PESEL");
            // table.addCell(appUser.getPesel());
            table.addCell("Login");
            table.addCell(appUser.getLogin());
            table.addCell("Email");
            table.addCell(appUser.getEmail());
            table.addCell("Active");
            table.addCell(String.valueOf(appUser.isEnabled()));
            pdf.add(table);
            pdf.close();
            o.close();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }

    public void generateBill(Visit visit, HttpServletResponse response) {
        try {
            AppUser appUser = visit.getPatient();
            OutputStream o1 = response.getOutputStream();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=" + appUser.getLogin() + ".pdf");
            Document pdf = new Document();
            PdfWriter.getInstance(pdf, o1);
            pdf.open();
            pdf.add(new Paragraph("BILL"));
            pdf.add(new Paragraph(Chunk.NEWLINE));
            pdf.add(new Paragraph(Chunk.NEWLINE));

            pdf.add(new Paragraph(appUser.getName() + " " + appUser.getSurname()));
            pdf.add(new Paragraph("Pesel: " + appUser.getPesel()));
            pdf.add(new Paragraph("Email: " + appUser.getEmail()));
            pdf.add(new Paragraph("Phone Number: " + appUser.getPhoneNumber()));
            pdf.add(new Paragraph(Chunk.NEWLINE));
            pdf.add(new Paragraph(Chunk.NEWLINE));

            PdfPTable pdfPTable = new PdfPTable(2);
            pdfPTable.addCell("Doctor");
            pdfPTable.addCell(visit.getVisitHours().getDoctor().getName() + " " + visit.getVisitHours().getDoctor().getSurname());
            pdfPTable.addCell("Description");
            pdfPTable.addCell(visit.getVisitHours().getDescription());
            pdfPTable.addCell("Date");
            pdfPTable.addCell(visit.getVisitHours().getStartDate().plusMinutes((visit.getVisitHours().getVisitLength())).toString());
            pdfPTable.addCell("Price");
            pdfPTable.addCell(visit.getVisitHours().getVisitCost().toString());
            pdf.add(pdfPTable);
            pdf.close();
            o1.close();

        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }

    }

}
