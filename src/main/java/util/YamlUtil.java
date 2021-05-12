package util;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;


public class YamlUtil<T> {

    private Yaml yaml = new Yaml();

    public T parseYamlFile(String fileName, Class<T> clazz) {
        try (InputStream inputStream = clazz.getResourceAsStream(fileName)) {
            return yaml.loadAs(inputStream, clazz);
        } catch (IOException e) {
            throw new AssertionError("File was not found " + e);
        }
    }
}
