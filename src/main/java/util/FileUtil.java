package util;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;


@UtilityClass
public class FileUtil {
// for further use and extension
    public InputStream readFile(String fileName) {
        try (InputStream inputStream = FileUtil.class.getResourceAsStream(fileName)) {
            return inputStream;
        } catch (IOException e) {
            throw new AssertionError("File was not found " + e);
        }
    }
}
