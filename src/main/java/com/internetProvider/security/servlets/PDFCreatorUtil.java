package com.internetProvider.security.servlets;

import com.internetProvider.dao.impl.CityDAOImpl;
import com.internetProvider.model.Tariff;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

public class PDFCreatorUtil {
    private final static Logger logger = Logger.getLogger(PDFCreatorUtil.class);

    public static boolean createTariff(Tariff tariff) {
        Document document = new Document();
        try {
            File file = new File("C:/Programs/Java/internetProvider/src/main/webapp/tariff.pdf");
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            Paragraph p = new Paragraph();
            p.add("Tariff Information");
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            document.add(new Paragraph(" "));
            PdfPTable table = createDefaultTariffTable();
            table.addCell(tariff.getName());
            table.addCell(tariff.getDescription());
            table.addCell(String.valueOf(tariff.getPrice()));
            table.addCell(String.valueOf(tariff.getDayDuration()));
            table.addCell(tariff.getFeatures().replace(";",";\n"));
            table.addCell(String.join(";\n", tariff.getListOfServiceName()));

            document.add(table);
        } catch (DocumentException | IOException e) {
            logger.error(e.getMessage());
            return false;
        } finally {
            document.close();
        }
        return true;
    }

    private static PdfPTable createDefaultTariffTable() {
        PdfPTable table = new PdfPTable(6);
        PdfPCell nameCell = new PdfPCell(new Paragraph("Name"));
        PdfPCell descriptionCell = new PdfPCell(new Paragraph("Description"));
        PdfPCell priceCell = new PdfPCell(new Paragraph("Price"));
        PdfPCell daysDurationCell = new PdfPCell(new Paragraph("Days duration"));
        PdfPCell featuresCell = new PdfPCell(new Paragraph("Features"));
        PdfPCell servicesCell = new PdfPCell(new Paragraph("Services"));
        table.addCell(nameCell);
        table.addCell(descriptionCell);
        table.addCell(priceCell);
        table.addCell(daysDurationCell);
        table.addCell(featuresCell);
        table.addCell(servicesCell);
        return table;
    }
}
