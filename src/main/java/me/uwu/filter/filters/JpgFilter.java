package me.uwu.filter.filters;

import me.uwu.filter.IFilter;

import java.io.File;
import java.io.IOException;

public class JpgFilter implements IFilter {
    @Override
    public boolean isValid(File file) throws IOException {
        return isWhat(file, "Exif") || isWhat(file, "JFIF");
    }

    @Override
    public String getName() {
        return "jpg";
    }
}
