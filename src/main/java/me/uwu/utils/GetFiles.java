package me.uwu.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class GetFiles {

    public static ArrayList<File> fromFolder(String folderPath){
        File f = new File(folderPath);
        return new ArrayList<>(Arrays.asList(Objects.requireNonNull(f.listFiles())));
    }

    public static ArrayList<File> fromSubFolders(String folderPath) {
        ArrayList<File> filesArray = new ArrayList<>();
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files != null)
        for (File file : files)
        {
            if (file.isFile()) {
                filesArray.add(file);
            }
            else if (file.isDirectory()) {
                filesArray.addAll(fromSubFolders(file.getAbsolutePath()));
            }
        }
        return filesArray;
    }
}
