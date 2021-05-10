package me.uwu.filter;

import me.uwu.utils.FastCopy;
import me.uwu.utils.FastDelete;
import me.uwu.utils.FileInfo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Filter {
    private final Map<String, Integer> stats;
    private final List<File> files;
    private final String tempPath;

    public static List<IFilter> filters;

    public Filter(Map<String, Integer> stats, List<File> files, String tempPath) {
        this.stats = stats;
        this.files = files;
        this.tempPath = tempPath;
    }

    public void filter(boolean keepUnknown) throws IOException {
        for (File file : files) {
            if(!file.isDirectory()) {
                boolean b = false;
                for (IFilter filter : filters) {
                    if (file.getAbsolutePath().endsWith("." + filter.getName())) {
                        FastCopy.file(file.getAbsolutePath(), tempPath + filter.getName() + "/" + file.getName());
                        FastDelete.file(file.getAbsolutePath());
                        incrementStats(filter.getName());
                        b = true;
                        break;
                    }
                }
                if (!b) {
                    for (IFilter filter : filters) {
                        if (filter.isValid(file)) {
                            FastCopy.file(file.getAbsolutePath(), tempPath + filter.getName() + "/" + file.getName() + "." + filter.getName());
                            FastDelete.file(file.getAbsolutePath());
                            incrementStats(filter.getName());
                            b = true;
                            break;
                        }
                    }
                }

                if (!b) {
                    if (keepUnknown) {
                        if (FileInfo.isTrashFile(file)) {
                            FastCopy.file(file.getAbsolutePath(), tempPath + "trash/" + file.getName());
                            FastDelete.file(file.getAbsolutePath());
                            incrementStats("trash");
                        } else {
                            FastCopy.file(file.getAbsolutePath(), tempPath + "unknown/" + file.getName());
                            FastDelete.file(file.getAbsolutePath());
                            incrementStats("other");
                        }
                    } else FastDelete.file(file.getAbsolutePath());
                }
            }
        }
    }

    private void incrementStats(String stat){
        stats.put(stat, stats.get(stat)+1);
    }
}
