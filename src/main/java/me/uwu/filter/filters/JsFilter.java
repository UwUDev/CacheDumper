package me.uwu.filter.filters;

import me.uwu.filter.IFilter;

import java.io.File;
import java.io.IOException;

public class JsFilter implements IFilter {
    @Override
    public boolean isValid(File file) throws IOException {
        return isWhat(file, "var") || isWhat(file, "width:") || isWhat(file, "jQuery") || isWhat(file, "Width:") || isWhat(file, "function()");
    }

    @Override
    public String getName() {
        return "js";
    }
}
