package com.example.searchcriteria.utility;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.searchcriteria.FileManipulation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility for file manipulation
 */
public class FileUtility implements FileManipulation {

    /**
     * {@inheritDoc}
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public List<String> populateFolderPath(String filepath) throws IOException {
        Stream<Path> pathStream = Files.walk(Paths.get(filepath));
        List<String> pathfiles = pathStream.filter(Files::isDirectory)
                .map(mappedfile -> mappedfile.toString()).collect(Collectors.toList());
        pathfiles.forEach(System.out::println);
        return pathfiles;
    }

    /**
     * {@inheritDoc}
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public List<String> populateFilePath(String filepath) throws IOException {
        List<String> pathfiles = getPathStream(filepath).filter(Files::isRegularFile)
                .map(mappedfile -> mappedfile.toString()).collect(Collectors.toList());
        pathfiles.forEach(System.out::println);
        return pathfiles;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private Stream<Path> getPathStream(String filePath) throws IOException
    {
        Stream<Path> pathStream = Files.walk(Paths.get(filePath));
        return pathStream;
    }
}
