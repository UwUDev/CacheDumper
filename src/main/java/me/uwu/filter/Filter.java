package me.uwu.filter;

import me.uwu.filter.filters.*;
import me.uwu.utils.FastCopy;
import me.uwu.utils.FastDelete;
import me.uwu.utils.FileInfo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Filter {
    private final Map<String, Integer> stats;
    private final List<File> files;
    private final String tempPath;

    private static List<IFilter> filters(){
        List<IFilter> filters = new ArrayList<>();
        filters.add(new DbFilter());
        filters.add(new GifFilter());
        filters.add(new GzFilter());
        filters.add(new IcoFilter());
        filters.add(new JpgFilter());
        filters.add(new JsFilter());
        filters.add(new LogFilter());
        filters.add(new Mp3Filter());
        filters.add(new Mp4Filter());
        filters.add(new PngFilter());
        filters.add(new WebmFilter());
        filters.add(new WebpFilter());
        filters.add(new WoffFilter());
        filters.add(new ZipFilter());
        filters.add(new JsonFilter());//  /!\  IMPOTANT, PUT THIS AT THE END  /!\

        return filters;
    }

    public Filter(Map<String, Integer> stats, List<File> files, String tempPath) {
        this.stats = stats;
        this.files = files;
        this.tempPath = tempPath;
    }

    public void filter() throws IOException {
        for (File file : files) {
            if(!file.isDirectory()) {
                boolean b = false;
                for (IFilter filter : filters()) {
                    if (file.getAbsolutePath().endsWith("." + filter.getName())) {
                        FastCopy.file(file.getAbsolutePath(), tempPath + filter.getName() + "/" + file.getName());
                        FastDelete.file(file.getAbsolutePath());
                        incrementStats(filter.getName());
                        b = true;
                        break;
                    }
                }
                if (!b) {
                    for (IFilter filter : filters()) {
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
                    if (FileInfo.isTrashFile(file)){
                        FastCopy.file(file.getAbsolutePath(), tempPath + "trash/" + file.getName());
                        FastDelete.file(file.getAbsolutePath());
                        incrementStats("trash");
                    } else {
                        FastCopy.file(file.getAbsolutePath(), tempPath + "unknown/" + file.getName());
                        FastDelete.file(file.getAbsolutePath());
                        incrementStats("other");
                    }
                }
            }
        }
    }

    private void incrementStats(String stat){
        stats.put(stat, stats.get(stat)+1);
    }
}
