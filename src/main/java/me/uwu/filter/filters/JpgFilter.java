package me.uwu.filter.filters;

import me.uwu.filter.IFilter;

import java.io.File;
import java.io.IOException;

public class JpgFilter implements IFilter {
    @Override
    public boolean isValid(File file) throws IOException {
        byte[] bytes = getBytes(file);
        try {
            return bytes[0] == (byte) 0xff && bytes[1] == (byte) 0xd8;
        } catch (ArrayIndexOutOfBoundsException ignored){}
        return false;
        //return isWhat(file, "Exif") || isWhat(file, "JFIF");
    }

    @Override
    public String getName() {
        return "jpg";
    }
}
