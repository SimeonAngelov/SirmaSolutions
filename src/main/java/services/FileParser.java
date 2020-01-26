package services;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class FileParser {
    public static final String NULL = "NULL";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String SEMICOLON = ";";

    private StringBuffer buffer = new StringBuffer();
    private String path;


    public FileParser(String path) {
        this.path = path;
    }

    public String readFile() throws FileNotFoundException {
        File file = new File(path);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            buffer.append(sc.nextLine());
            buffer.append(SEMICOLON);
        }
        return buffer.toString();
    }

    public String replaceNullValues(String text){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(YYYY_MM_DD);
        LocalDateTime now = LocalDateTime.now();
        return text.replaceAll(NULL,dtf.format(now));
    }
}
