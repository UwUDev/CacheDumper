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
}
