package utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class PropertyManager {

    private Properties properties;

    public PropertyManager() {
        this.properties = new Properties();
        try {
            properties.load(new FileReader(new File(ClassLoader.getSystemResource("config.properties").getPath())));
        } catch (IOException ex) {
            System.out.println("Properties file not found!");
        }
    }

    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new IllegalArgumentException("Property with key '" + key + "' not found in config.properties file.");
        }
        return properties.getProperty(key);
    }
}
