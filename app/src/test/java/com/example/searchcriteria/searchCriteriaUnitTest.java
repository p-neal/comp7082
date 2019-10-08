package com.example.searchcriteria;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Unit test for Task 10
 */
public class searchCriteriaUnitTest {

    private searchCriteria searchCriteria;
    private String filePath;
    private List<String> fileList;

    public searchCriteriaUnitTest()
    {
        filePath = "C:\\projects";
    }

    /**
     * Test: search for files within filepath
     * @throws IOException
     */
    @Test
    public void testSearchFilePath() throws IOException {
        searchCriteria = new searchCriteria(Criteria.files, filePath);
        assertNotNull(searchCriteria);
        fileList = searchCriteria.searchPath();
        assertNotNull(fileList);
    }

    /**
     * Test: search for folders within filepath
     * @throws IOException
     */
    @Test
    public void testSearchFolderPath() throws IOException {
        searchCriteria = new searchCriteria(Criteria.folders, filePath);
        assertNotNull(searchCriteria);
        fileList = searchCriteria.searchPath();
        assertNotNull(fileList);
    }
}