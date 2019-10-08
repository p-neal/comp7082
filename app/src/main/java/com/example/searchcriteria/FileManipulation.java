package com.example.searchcriteria;

import java.io.IOException;
import java.util.List;

public interface FileManipulation {
    /**
     * Populate a list of folder paths
     * @param filepath
     * @return List<String> of folder paths
     * @throws IOException
     */
    List<String> populateFolderPath(String filepath) throws IOException;

    /**
     * Populate a list of file paths
     * @param filepath
     * @return List<String> of file paths
     * @throws IOException
     */
    List<String> populateFilePath(String filepath) throws IOException;
}
