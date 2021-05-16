package me.uwu.filter.filters;

import me.uwu.filter.IFilter;

import java.io.File;
import java.io.IOException;

public class SvgFilter implements IFilter {
    @Override
    public boolean isValid(File file) throws IOException {
        return headerContains(file,"<svg");
    }

    @Override
    public String getName() {
        return "svg";
    }
}
