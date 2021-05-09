package me.uwu.filter.filters;

import me.uwu.filter.IFilter;

import java.io.File;
import java.io.IOException;

public class WoffFilter implements IFilter {
    @Override
    public boolean isValid(File file) throws IOException {
        return isWhat(file,"wOFF");
    }

    @Override
    public String getName() {
        return "woff";
    }
}
