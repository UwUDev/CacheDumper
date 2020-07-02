package me.uwu.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileInfo {

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

    public static boolean isWhat(String file, String contains) throws IOException {

        FileInputStream inputStream = null;
        Scanner sc = null;
        boolean test = false;
        try {
            inputStream = new FileInputStream(file);
            sc = new Scanner(inputStream, "UTF-8");

            String line = sc.nextLine();
            // System.out.println(line);

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

}
