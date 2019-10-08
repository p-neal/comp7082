package com.example.searchcriteria;

import java.io.IOException;
import java.util.List;

public interface FileManipulation {
    List<String> populateFolderPath(String filepath) throws IOException;
    List<String> populateFilePath(String filepath) throws IOException;
}
