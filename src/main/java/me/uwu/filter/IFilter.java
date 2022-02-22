package me.uwu.filter;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public interface IFilter {
    boolean isValid(File file) throws IOException;
    String getName();

    default boolean headerContains(File f, String contains) throws IOException {

        FileInputStream inputStream = null;
        Scanner sc = null;
        boolean test = false;
        try {
            inputStream = new FileInputStream(f.getAbsolutePath());
            sc = new Scanner(inputStream, "UTF-8");

            if (f.length() ==0){
                inputStream.close();
                sc.close();
                return false;
            }

            String line = sc.nextLine();

            if(line.contains(contains)){
                test = true;
            }


            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }

        return test;
    }

    default byte[] getBytes(File file) throws IOException {
        return FileUtils.readFileToByteArray(file);
    }
}
