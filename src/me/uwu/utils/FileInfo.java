package me.uwu.utils;

import me.uwu.Main;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileInfo {

    private static final Logger logger = Logger.getLogger(FileInfo.class);

    public static boolean isPNG(String file) throws IOException {
        return isWhat(file,"PNG");
    }

    public static boolean isJPG(String file) throws IOException {
        return isWhat(file, "Exif") || isWhat(file, "JFIF");
    }

    public static boolean isGIF(String file) throws IOException {
        return isWhat(file,"GIF");
    }

    public static boolean isWEBM(String file) throws IOException {
        return isWhat(file,"webm");
    }

    public static boolean isWEBP(String file) throws IOException {
        return isWhat(file,"WEBP");
    }

    public static boolean isMP4(String file) throws IOException {
        return isWhat(file,"ftyp");
    }

    public static boolean isMP3(String file) throws IOException {
        return isWhat(file,"ID3");
    }

    public static boolean isZIP(String file) throws IOException {
        return isWhat(file,"PK");
    }

    public static boolean isGZ(String file) throws IOException {
        return isWhat(file,"\u001F�\b");
    }

    public static boolean isWOFF(String file) throws IOException {
        return isWhat(file,"wOFF");
    }

    public static boolean isSVG(String file) throws IOException {
        return isWhat(file,"<svg");
    }

    public static boolean isJSON(String file) throws IOException {
        return isWhat(file,"{");
    }

    public static boolean isJS(String file) throws IOException {
        return isWhat(file, "var") || isWhat(file, "width:") || isWhat(file, "jQuery") || isWhat(file, "Width:") || isWhat(file, "function()");
    }

    public static boolean isWhat(String filePath, String contains) throws IOException {

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
