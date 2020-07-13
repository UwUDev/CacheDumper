package me.uwu.utils;

import me.uwu.Main;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;

public class FastDelete {

    private static final Logger logger = Logger.getLogger(FastDelete.class);

    public static void file(String file){
        File fi1 = new File(file);

        logger.info("Try to delete " +file + TimeUtils.dateAndTime());

        FileUtils.deleteQuietly(fi1);
    }

    public static void folder(String folder){
        File fo1 = new File(folder);

        logger.info("Try to delete " +folder + TimeUtils.dateAndTime());

        FileUtils.deleteQuietly(fo1);
    }

}
