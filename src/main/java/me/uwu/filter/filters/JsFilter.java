package me.uwu.filter.filters;

import me.uwu.filter.IFilter;

import java.io.File;
import java.io.IOException;

public class JsFilter implements IFilter {
    @Override
    public boolean isValid(File file) throws IOException {
        return headerContains(file, "var") || headerContains(file, "width:") || headerContains(file, "jQuery") || headerContains(file, "Width:") || headerContains(file, "function()");
    }

    @Override
    public String getName() {
        return "js";
    }
}
