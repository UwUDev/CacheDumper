package me.uwu.filter.filters;

import me.uwu.filter.IFilter;

import java.io.File;
import java.io.IOException;

public class Mp4Filter implements IFilter {
    @Override
    public boolean isValid(File file) throws IOException {
        return isWhat(file,"ftyp");
    }

    @Override
    public String getName() {
        return "mp4";
    }
}
