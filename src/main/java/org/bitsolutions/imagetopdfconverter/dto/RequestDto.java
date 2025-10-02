package org.bitsolutions.imagetopdfconverter.dto;

public class RequestDto {
    private String folderPath;
    private String outputPdfPath;

    public String getFolderPath() { return folderPath; }
    public void setFolderPath(String folderPath) { this.folderPath = folderPath; }

    public String getOutputPdfPath() { return outputPdfPath; }
    public void setOutputPdfPath(String outputPdfPath) { this.outputPdfPath = outputPdfPath; }
}