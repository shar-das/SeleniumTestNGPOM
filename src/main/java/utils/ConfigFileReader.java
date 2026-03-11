package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    static Properties prop;

    static {
        try {
            FileInputStream file = new FileInputStream("src/main/resources/config.properties");
            prop = new Properties();
            prop.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String propertyName) {
        return prop.getProperty(propertyName);
    }

}
