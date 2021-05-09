package me.uwu.filter.filters;

import me.uwu.filter.IFilter;

import java.io.File;
import java.io.IOException;

public class Mp3Filter implements IFilter {
    @Override
    public boolean isValid(File file) throws IOException {
        return isWhat(file,"ID3");
    }

    @Override
    public String getName() {
        return "mp3";
    }
}
