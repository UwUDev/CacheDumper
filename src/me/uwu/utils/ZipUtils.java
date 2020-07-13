package me.uwu.utils;

import me.uwu.Main;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.log4j.Logger;

public class ZipUtils {

    private static final Logger logger = Logger.getLogger(ZipUtils.class);

    public static void unzip(String compressedFile, String decompressedFolder){

        try {
            logger.info("Try to unzip " + compressedFile + " to " + decompressedFolder);
            ZipFile zipFile = new ZipFile(compressedFile);
            zipFile.extractAll(decompressedFolder);
        } catch (ZipException e) {
            logger.error("Failed to unzip " + compressedFile,e);
        }
    }

}
