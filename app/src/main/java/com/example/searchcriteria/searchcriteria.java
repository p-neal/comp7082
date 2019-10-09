package com.example.searchcriteria;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.searchcriteria.utility.FileUtility;

import java.io.IOException;
import java.util.List;

/**
 * Search files and folders
 */
class searchCriteria {
    protected String filePath;
    protected Criteria searchCriteria;
    FileUtility fileUtility;

    public searchCriteria(Criteria searchCriteria, String filePath){
        fileUtility = new FileUtility();
        this.searchCriteria = searchCriteria;
        this.filePath = filePath;
    }

    /**
     * Search paths based on criteria
     * @return filteredPath
     * @throws IOException
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<String> searchPath() throws IOException {

        List<String> filteredPath = null;

        switch(searchCriteria){
            case files:
                filteredPath = fileUtility.populateFilePath(filePath);
                break;
            case folders:
                filteredPath = fileUtility.populateFolderPath(filePath);
                break;
        }

        return filteredPath;
    }
}
