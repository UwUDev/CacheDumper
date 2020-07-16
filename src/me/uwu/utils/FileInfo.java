package me.uwu.utils;

import org.apache.log4j.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileInfo {

    private static final Logger logger = Logger.getLogger(FileInfo.class);

    public static boolean isPNG(String filePath) throws IOException {
        return isWhat(filePath,"PNG");
    }

    public static boolean isJPG(String filePath) throws IOException {
        return isWhat(filePath, "Exif") || isWhat(filePath, "JFIF");
    }

    public static boolean isGIF(String filePath) throws IOException {
        return isWhat(filePath,"GIF");
    }

    public static boolean isWEBM(String filePath) throws IOException {
        return isWhat(filePath,"webm");
    }

    public static boolean isWEBP(String filePath) throws IOException {
        return isWhat(filePath,"WEBP");
    }

    public static boolean isMP4(String filePath) throws IOException {
        return isWhat(filePath,"ftyp");
    }

    public static boolean isMP3(String filePath) throws IOException {
        return isWhat(filePath,"ID3");
    }

    public static boolean isZIP(String filePath) throws IOException {
        return isWhat(filePath,"PK");
    }

    public static boolean isGZ(String filePath) throws IOException {
        return isWhat(filePath,"\u001F�\b");
    }

    public static boolean isWOFF(String filePath) throws IOException {
        return isWhat(filePath,"wOFF");
    }

    public static boolean isSVG(String filePath) throws IOException {
        return isWhat(filePath,"<svg");
    }

    public static boolean isJSON(String filePath) throws IOException {
        return isWhat(filePath,"{");
    }

    public static boolean isJS(String filePath) throws IOException {
        return isWhat(filePath, "var") || isWhat(filePath, "width:") || isWhat(filePath, "jQuery") || isWhat(filePath, "Width:") || isWhat(filePath, "function()");
    }

    public static boolean isDB(String filePath) throws IOException {
        return isWhat(filePath,"SQLite");
    }

    public static boolean isWhat(String filePath, String contains) throws IOException {

        File f = new File(filePath);
        if (f.length() ==0){
            logger.warn("File is null");
            return false;
        }

        FileInputStream inputStream = null;
        Scanner sc = null;
        boolean test = false;
        try {
            inputStream = new FileInputStream(filePath);
            sc = new Scanner(inputStream, "UTF-8");

            String line = sc.nextLine();
            logger.debug("First line of the file is : " + line);

            if(line.contains(contains)){
                test = true;
            }


            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }

        return test;

    }

    public static boolean isTrashFile(String filePath){
        boolean isTrash = false;
        File f = new File(filePath);

        logger.debug("File size is : " + f.length());

        if (f.length() == 1048576 || f.length() == 1048344) isTrash = true;
        //1048344 c'est quand windows compresse les dossier avec les fleches bleues

        return isTrash;
    }

    private static final Pattern urlPattern = Pattern.compile(
            "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                    + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                    + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

    public static String getLinksFromFile (String filePath) throws IOException {

        StringBuilder sb = new StringBuilder();

        FileInputStream inputStream = null;
        Scanner sc = null;
        boolean test = false;
        try {
            inputStream = new FileInputStream(filePath);
            sc = new Scanner(inputStream, "UTF-8");



            String line = sc.next();

            while (sc.hasNext()){


                line = sc.next();
                Matcher matcher = urlPattern.matcher(line);
                while (matcher.find()) {
                    int matchStart = matcher.start(1);
                    int matchEnd = matcher.end();

                    final String replace = line.substring(matchStart, matchEnd).replace("0", "");

                    if(!sb.toString().contains(replace)) {
                        sb.append(replace).append("\n");
                    }
                }
            }

            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }

        return sb.toString();

    }

}
