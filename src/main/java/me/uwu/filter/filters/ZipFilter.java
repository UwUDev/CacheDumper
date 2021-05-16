package me.uwu.filter.filters;

import me.uwu.filter.IFilter;

import java.io.File;
import java.io.IOException;

public class ZipFilter implements IFilter {
    @Override
    public boolean isValid(File file) throws IOException {
        return headerContains(file,"PK");
    }

    @Override
    public String getName() {
        return "zip";
    }
}
