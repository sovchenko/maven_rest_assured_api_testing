package util;

import lombok.experimental.UtilityClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@UtilityClass
public class FileUtil {
//    File readFile(String path){
//        return new File(path);
//    }

    public FileInputStream readFile(String path) {
        try {
            return new FileInputStream(path);
        } catch (FileNotFoundException e) {
            throw new AssertionError("File was not found " + e);
        }
    }
}
