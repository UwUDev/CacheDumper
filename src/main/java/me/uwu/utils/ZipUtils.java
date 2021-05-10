package me.uwu.utils;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class ZipUtils {

    public static void unzip(String compressedFile, String decompressedFolder){

        try {
            System.out.println("Try to unzip " + compressedFile + " to " + decompressedFolder);
            ZipFile zipFile = new ZipFile(compressedFile);
            zipFile.extractAll(decompressedFolder);
        } catch (ZipException e) {
            System.out.println("Failed to unzip " + compressedFile);
            e.printStackTrace();
        }
    }
}
