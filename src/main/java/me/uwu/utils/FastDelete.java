package me.uwu.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;

public class FastDelete {
    public static void file(String file){
        File fi1 = new File(file);
        System.out.println("Try to delete " + file + TimeUtils.dateAndTime());
        FileUtils.deleteQuietly(fi1);
    }

    public static void folder(String folder){
        File fo1 = new File(folder);
        System.out.println("Try to delete " + folder + TimeUtils.dateAndTime());
        FileUtils.deleteQuietly(fo1);
    }
}
