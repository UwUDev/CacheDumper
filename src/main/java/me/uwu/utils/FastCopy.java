package me.uwu.utils;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

public class FastCopy {
    public static void file(String fileIn, String fileOut){
        File fi1 = new File(fileIn);
        File fi2 = new File(fileOut);

        System.out.println("Try to copy " + fileIn + " to " + fileOut);

        try {
            FileUtils.copyFile(fi1, fi2);
            System.out.println("Successfully copied " + fileIn + " to " + fileOut);
        } catch (IOException e) {
            System.out.println("Failed to copy " + fileIn + " to " + fileOut);
            e.printStackTrace();
        }
    }

    public static void folder(String folderIn, String folderOut){
        File srcFo1 = new File(folderIn);
        File srcFo2 = new File(folderOut);

        System.out.println("Try to copy " + folderIn + " to " + folderOut + TimeUtils.dateAndTime());

        try {
            FileUtils.copyDirectory(srcFo1, srcFo2);
            System.out.println("Successfully copied " + folderIn + " to " + folderOut);
        } catch (IOException e) {
            System.out.println("Failed to copy " + folderIn + " to " + folderOut);
            e.printStackTrace();
        }
    }

}
