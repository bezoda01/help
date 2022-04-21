package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {
    public static String readFile(String path) {
        String text = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text+=line+"\n";
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}
