package com.farid.azayev.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomFileReader {
    private String filePath;

    public CustomFileReader(String filePath) {
        this.filePath = filePath;
    }

    public List<String> getFileAsStringArray () throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        String[] lines = StringUtils.split(FileUtils.readFileToString(
                new File(classLoader.getResource(filePath).getFile())), '\n');
        return new ArrayList<>(Arrays.asList(lines));
    }
}