package com.farid.azayev.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TrainsAndTowersAppProperties implements AppProperties {
    private Properties properties;
    private final String propertiesFileName = "application.properties";

    public TrainsAndTowersAppProperties() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        properties = new Properties();
        try {
            properties.load(new FileInputStream(classLoader.getResource(propertiesFileName).getFile()));
        } catch (IOException e) {
            throw e;
        }
    }

    public String getProperty(String key) throws RuntimeException {
        String p = properties.getProperty(key);
        if(p==null) throw new RuntimeException(String.format("No property with name %s found",key));
        return properties.getProperty(key);
    }
}
