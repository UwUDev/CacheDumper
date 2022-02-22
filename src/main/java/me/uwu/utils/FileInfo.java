package me.uwu.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileInfo {

    public static boolean isTrashFile(File f){
        return f.length() == 1048576 || f.length() == 1048344;
    }

    @SuppressWarnings("RegExpRedundantEscape")
    private static final Pattern urlPattern = Pattern.compile(
            "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                    + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                    + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

    public static String getLinksFromFile (String filePath) {
        StringBuilder sb = new StringBuilder();

        try (FileInputStream inputStream = new FileInputStream(filePath); Scanner sc = new Scanner(inputStream, "UTF-8")) {
            String line;

            while (sc.hasNext()) {
                line = sc.next();
                Matcher matcher = urlPattern.matcher(line);
                while (matcher.find()) {
                    int matchStart = matcher.start(1);
                    int matchEnd = matcher.end();

                    final String replace = line.substring(matchStart, matchEnd).replace("0", "");

                    if (!sb.toString().contains(replace)) {
                        sb.append(replace).append("\n");
                    }
                }
            }

            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
