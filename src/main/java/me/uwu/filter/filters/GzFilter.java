package me.uwu.filter.filters;

import me.uwu.filter.IFilter;

import java.io.File;
import java.io.IOException;

public class GzFilter implements IFilter {
    @Override
    public boolean isValid(File file) throws IOException {
        return headerContains(file,"\u001F�\b");
    }

    @Override
    public String getName() {
        return "gz";
    }
}
