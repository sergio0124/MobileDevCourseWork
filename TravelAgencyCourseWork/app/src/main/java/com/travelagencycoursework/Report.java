package com.travelagencycoursework;

import android.os.Environment;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.travelagencycoursework.database.models.TripModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Report {

    final int TABLE_WIDTH = 400;
    final int COLUMN_COUNT = 5;
    String[] columns = {"Name", "Place", "Guide", "Price", "Date"};
    String date;

    public void generatePdf(List<TripModel> tripModels, Date dateFrom, Date dateTo) throws IOException {

        String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(pdfPath, "report.pdf");

        PdfWriter pdfWriter = new PdfWriter(new FileOutputStream(file));
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);

        date = dateFrom.getDate() + " / " + dateFrom.getMonth() + " / " + (dateFrom.getYear() + 1900) +
                " to " + dateTo.getDate() + " / " + dateTo.getMonth() + " / " + (dateTo.getYear() + 1900);
        Paragraph paragraph = new Paragraph("Report on the number of prescribed tripModels for" +
                " the period from " + date);

        document.add(paragraph);
        if(tripModels.size()>0){
            Paragraph paragraphq = new Paragraph("Trips created by user with login " + tripModels.get(0).getUserLogin());
            document.add(paragraphq);
        }

        float columnWidth[] = new float[COLUMN_COUNT];

        float size = TABLE_WIDTH / COLUMN_COUNT;

        for (int i = 0; i < columnWidth.length; i++) {
            columnWidth[i]=size;
        }

        Table table = new Table(columnWidth);

        for (int i = 0; i < columnWidth.length; i++) {
            table.addCell(columns[i]);
        }

        for (TripModel trip : tripModels) {
            table.addCell(trip.getName());
            table.addCell(trip.getPlaceName());
            table.addCell(trip.getGuideName());
            table.addCell(String.valueOf(trip.getPrice()));
            Date tripDate = trip.getDate().getTime();
            date = tripDate.getDate() + " / " + tripDate.getMonth() + " / " + (tripDate.getYear() + 1900);
            table.addCell(date);
        }

        document.add(table);
        document.close();
    }

}