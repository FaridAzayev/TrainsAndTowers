package com.farid.azayev;

import com.farid.azayev.config.TrainsAndTowersAppProperties;
import com.farid.azayev.utils.*;

import java.io.*;

public class Main {
    public static void main (String[] args) throws IOException {
        TrainsAndTowersAppProperties prop = new TrainsAndTowersAppProperties();
        final String inputFilePath = prop.getProperty("input-file-path");

        new CustomFileReader(inputFilePath).getFileAsStringArray().forEach(x-> {
            try {
                new Reporter(new GraphParser(x).getGraph()).report();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
