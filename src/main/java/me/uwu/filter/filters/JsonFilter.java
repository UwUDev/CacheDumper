package me.uwu.filter.filters;

import me.uwu.filter.IFilter;

import java.io.File;
import java.io.IOException;

public class JsonFilter implements IFilter {
    @Override
    public boolean isValid(File file) throws IOException {
        return isWhat(file, "{") || isWhat(file, "[");
    }

    @Override
    public String getName() {
        return "json";
    }
}
