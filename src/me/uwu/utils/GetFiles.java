package me.uwu.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class GetFiles {

    public static ArrayList<File> fromFolder(String folderPath){
        File f = new File(folderPath);
        return new ArrayList<>(Arrays.asList(Objects.requireNonNull(f.listFiles())));
    }

    public static Collection fromSubFolders(String folderPath) {
        return FileUtils.listFiles(new File(folderPath), new RegexFileFilter("^(.*?)"), DirectoryFileFilter.DIRECTORY);
    }
}
