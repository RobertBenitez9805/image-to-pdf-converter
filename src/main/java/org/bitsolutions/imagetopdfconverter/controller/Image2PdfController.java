package org.bitsolutions.imagetopdfconverter.controller;

import org.bitsolutions.imagetopdfconverter.dto.RequestDto;
import org.bitsolutions.imagetopdfconverter.service.Image2PdfService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pdf")
public class Image2PdfController {

    private final Image2PdfService service;

    public Image2PdfController(Image2PdfService service) {
        this.service = service;
    }

    @PostMapping("/from-folder")
    public ResponseEntity<String> fromFolder(@RequestBody RequestDto dto) {
        try {
            service.convert(dto.getFolderPath(), dto.getOutputPdfPath());
            return ResponseEntity.ok("Converted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
