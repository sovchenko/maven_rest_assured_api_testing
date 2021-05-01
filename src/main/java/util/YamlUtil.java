package util;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

public class YamlUtil<T> {

    private Yaml yaml = new Yaml();

    public T parseYaml(InputStream inputStream, Class<T> clazz) {
        return yaml.loadAs(inputStream, clazz);
    }

    public T createPojoFromYaml(String yamlPath){
        try {

        }

        parseYaml()
    }
}
