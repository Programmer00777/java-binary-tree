package utils;

import java.io.*;
import java.util.Scanner;

public class FileReader {
    public String readFile(String filePath) throws IOException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        StringBuilder data = new StringBuilder();
        while (scanner.hasNextLine()) {
            data.append(scanner.nextLine() + "\n");
        }

        return data.toString();
    }
}