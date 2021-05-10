package me.uwu.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class GZipUtils {
    public static void unGzipFile(String compressedFile, String decompressedFile) {
        System.out.println("Try to decompress : " + compressedFile);
        byte[] buffer = new byte[1024];

        try {
            FileInputStream fileIn = new FileInputStream(compressedFile);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(fileIn);
            FileOutputStream fileOutputStream = new FileOutputStream(decompressedFile);

            int bytes_read;

            while ((bytes_read = gZIPInputStream.read(buffer)) > 0) {

                fileOutputStream.write(buffer, 0, bytes_read);
            }

            gZIPInputStream.close();
            fileOutputStream.close();

            System.out.println("The file was decompressed successfully!");

        } catch (IOException ex) {
            System.out.println("Failed to unzip " + compressedFile + " to " + decompressedFile);
            ex.printStackTrace();
        }

    }

}
