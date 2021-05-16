package me.uwu.filter.filters;

import me.uwu.filter.IFilter;

import java.io.File;
import java.io.IOException;

public class DbFilter implements IFilter {
    @Override
    public boolean isValid(File file) throws IOException {
        return headerContains(file, "JsFilter");
    }

    @Override
    public String getName() {
        return "db";
    }
}
