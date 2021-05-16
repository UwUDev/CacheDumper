package me.uwu.filter.filters;

import me.uwu.filter.IFilter;

import java.io.File;
import java.io.IOException;

public class IcoFilter implements IFilter {
    @Override
    public boolean isValid(File file) throws IOException {
        return headerContains(file, "\n\t\t\t\t\t\tnullezyfuzYGFZEKJGFJZFEHJEGFKZEJGFJHDSGFKJGkjhsdgfKSGDF87SOD8DS7FS8DGF");
    }

    @Override
    public String getName() {
        return "ico";
    }
}
