package com.example.searchcriteria;

public class FileDetails {
    private String fileName;
    private String filepath;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public String toString() {
        return "FileDetails{" +
                "fileName='" + fileName + '\'' +
                ", filepath='" + filepath + '\'' +
                '}';
    }
}
