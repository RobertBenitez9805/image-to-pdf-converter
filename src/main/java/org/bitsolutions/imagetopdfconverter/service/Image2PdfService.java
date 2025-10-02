package org.bitsolutions.imagetopdfconverter.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

@Service
public class Image2PdfService {
    public void convert(String inputPath, String outputPath) throws IOException {
        File folder = new File(inputPath);
        if(!folder.exists() || !folder.isDirectory()) {
            throw new IllegalArgumentException("Input path does not exist or is not a directory");
        }

        File[] listOfFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg"));

        if (listOfFiles == null || listOfFiles.length == 0) {
            throw new IllegalArgumentException("Input path does not exist or is not a file");
        }

        Arrays.sort(listOfFiles, (f1, f2) -> {
            try {
                int n1 = Integer.parseInt(f1.getName().replaceAll("\\D", ""));
                int n2 = Integer.parseInt(f2.getName().replaceAll("\\D", ""));
                return Integer.compare(n1, n2);
            } catch (NumberFormatException e) {
                return f1.getName().compareTo(f2.getName());
            }
        });

//        for (File file : listOfFiles) {
//            System.out.println(file.getName());
//        }

        try (PDDocument doc = new PDDocument()) {
            for (File imgFile : listOfFiles) {
                BufferedImage bimg = ImageIO.read(imgFile);
                if (bimg == null) continue;

                PDPage page = new PDPage(PDRectangle.LETTER);
                doc.addPage(page);

                PDImageXObject pdImage = PDImageXObject.createFromFileByContent(imgFile, doc);

                try (PDPageContentStream content = new PDPageContentStream(doc, page)) {
                    float pageWidth = page.getMediaBox().getWidth();
                    float pageHeight = page.getMediaBox().getHeight();

                    float imgW = pdImage.getWidth();
                    float imgH = pdImage.getHeight();

                    float scale = Math.min(pageWidth / imgW, pageHeight / imgH);
                    float drawW = imgW * scale;
                    float drawH = imgH * scale;

                    float x = (pageWidth - drawW) / 2f;
                    float y = (pageHeight - drawH) / 2f;

                    content.drawImage(pdImage, x, y, drawW, drawH);
                }
            }
            doc.save(outputPath);
        }
    }
}
