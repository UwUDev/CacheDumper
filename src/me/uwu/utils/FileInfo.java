package me.uwu.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FileInfo {

    public static boolean isPNG(String file) throws IOException {

        FileInputStream inputStream = null;
        Scanner sc = null;
        boolean test = false;
        try {
            inputStream = new FileInputStream(file);
            sc = new Scanner(inputStream, "UTF-8");

                String line = sc.nextLine();
               // System.out.println(line);

                if(line.contains("PNG")){
                    test = true;
                }else{
                    test = false;
                }


            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

    public static boolean isJPG(String file) throws IOException {

        FileInputStream inputStream = null;
        Scanner sc = null;
        boolean test = false;
        try {
            inputStream = new FileInputStream(file);
            sc = new Scanner(inputStream, "UTF-8");

            String line = sc.nextLine();
            // System.out.println(line);

            if(line.contains("Exif") || line.contains("JFIF")){
                test = true;
            }else{
                test = false;
            }


            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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



    public static boolean isGIF(String file) throws IOException {

        FileInputStream inputStream = null;
        Scanner sc = null;
        boolean test = false;
        try {
            inputStream = new FileInputStream(file);
            sc = new Scanner(inputStream, "UTF-8");

            String line = sc.nextLine();
            // System.out.println(line);

            if(line.contains("GIF")){
                test = true;
            }else{
                test = false;
            }


            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

    public static boolean isWEBM(String file) throws IOException {

        FileInputStream inputStream = null;
        Scanner sc = null;
        boolean test = false;
        try {
            inputStream = new FileInputStream(file);
            sc = new Scanner(inputStream, "UTF-8");

            String line = sc.nextLine();
            // System.out.println(line);

            if(line.contains("webm")){
                test = true;
            }else{
                test = false;
            }


            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

    public static boolean isMP4(String file) throws IOException {

        FileInputStream inputStream = null;
        Scanner sc = null;
        boolean test = false;
        try {
            inputStream = new FileInputStream(file);
            sc = new Scanner(inputStream, "UTF-8");

            String line = sc.nextLine();
            // System.out.println(line);

            if(line.contains("ftyp")){
                test = true;
            }else{
                test = false;
            }


            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

}