package me.uwu.utils;

import me.uwu.Main;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class GZipUtils {

    private static final Logger logger = Logger.getLogger(GZipUtils.class);

    public static void unGzipFile(String compressedFile, String decompressedFile) {

        logger.info("Try to decompress : " + compressedFile);

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

            logger.info("The file was decompressed successfully!");

        } catch (IOException ex) {
            logger.error("Failed to unzip " + compressedFile + " to " + decompressedFile, ex);
            //ex.printStackTrace();
        }

    }

}
