package Utilities;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.WebDriver;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;


public class PDFReader {

    public static String readPDFFromURL(WebDriver driver) {
        String text = null;
//        try {
//            URL url = new URL(driver.getCurrentUrl());
//            BufferedInputStream file = new BufferedInputStream(url.openStream());
//            PDDocument document = null;
//            try {
//                document = PDDocument.load(file);
//                text = new PDFTextStripper().getText(document);
//                return text;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//       }
//
        return text;
    }


    public static boolean textExistsInPdf(WebDriver driver, String text) {


        return false;
    }

    public static PDDocument openPDF(WebDriver driver) {
        PDDocument document = null;

        return document;
    }

    public static void closePDFDoc(PDDocument document) {
        try {
            if (document != null) {
                document.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
