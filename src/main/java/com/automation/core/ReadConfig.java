package com.automation.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ReadConfig Class has methods to read properties file from resources folder
 * 
 * @author ngupta
 *
 */
public class ReadConfig {

    /**
     * Below method is used to read properties
     * 
     * @author ngupta
     * @return Properties
     * @throws IOException 
     */

    public Properties readPropertiesFile(String fileName) throws IOException {
        Properties prop = new Properties();
        prop.load(getFileFromResourceAsStream(fileName));
        return prop;
    }

    
    /**
     * Below method is used to read files from resources folder
     * 
     * @author ngupta
     * @return InputStream
     */
    private InputStream getFileFromResourceAsStream(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }
}
