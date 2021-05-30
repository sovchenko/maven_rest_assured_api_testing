package util;

import exceptions.ReadFileException;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


@UtilityClass
public class FileUtil {

    private static String getProperty(String propertyFileName, String propertyName) {
        try (InputStream inputStream = FileUtil.class.getClassLoader().getResourceAsStream(propertyFileName)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties.getProperty(propertyName);
        } catch (IOException e) {
            throw new ReadFileException("Not able to read property file");
        }
    }

    public static String getConfigProperty(String propertyName) {
        String configFileName = "config.properties";
        return getProperty(configFileName, propertyName);
    }
}
