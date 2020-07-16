package me.uwu.utils;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import java.io.File;
import java.io.IOException;

public class FastCopy {

    private static final Logger logger = Logger.getLogger(FastCopy.class);

    public static void file(String fileIn, String fileOut){
        File fi1 = new File(fileIn);
        File fi2 = new File(fileOut);

        logger.info("Try to copy " + fileIn + " to " + fileOut);

        try {
            FileUtils.copyFile(fi1, fi2);
            logger.info("Successfully copied " + fileIn + " to " + fileOut);
        } catch (IOException e) {
            logger.error("Failed to copy " + fileIn + " to " + fileOut,e);
        }
    }

    public static void folder(String folderIn, String folderOut){
        File srcFo1 = new File(folderIn);
        File srcFo2 = new File(folderOut);

        logger.info("Try to copy " + folderIn + " to " + folderOut + TimeUtils.dateAndTime());

        try {
            FileUtils.copyDirectory(srcFo1, srcFo2);
            logger.info("Successfully copied " + folderIn + " to " + folderOut);
        } catch (IOException e) {
            logger.error("Failed to copy " + folderIn + " to " + folderOut, e);
        }
    }

}
