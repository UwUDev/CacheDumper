package me.uwu.utils;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class ZipUtils {

    public static void unzip(String compressedFile, String decompressedFolder){

        try {
            ZipFile zipFile = new ZipFile(compressedFile);
            zipFile.extractAll(decompressedFolder);
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }

}
