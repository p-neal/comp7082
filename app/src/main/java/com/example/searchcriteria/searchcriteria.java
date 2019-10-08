package com.example.searchcriteria;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.searchcriteria.utility.FileUtility;

import java.io.IOException;
import java.util.List;

public class searchcriteria {
    protected String filePath;
    protected Criteria searchCriteria;
    FileUtility fileUtility;

    public searchcriteria(Criteria searchCriteria, String filePath){
        fileUtility = new FileUtility();
        this.searchCriteria = searchCriteria;
        this.filePath = filePath;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<String> searchFilePath(Criteria searchCriteria, String filePath) throws IOException {

        List<String> filteredPath = null;

        switch(searchCriteria){
            case files:
                filteredPath = fileUtility.populateFilePath(filePath);
                break;
            case folders:
                filteredPath = fileUtility.populateFilePath(filePath);
                break;
        }

        return filteredPath;
    }
}
