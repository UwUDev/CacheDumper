package me.uwu.filter.filters;

import me.uwu.filter.IFilter;

import java.io.File;
import java.io.IOException;

public class LdbFilter implements IFilter {
    @Override
    public boolean isValid(File file) throws IOException {
        return headerContains(file, "SQLite") || headerContains(file, "sqlite");
    }

    @Override
    public String getName() {
        return "ldb";
    }
}
