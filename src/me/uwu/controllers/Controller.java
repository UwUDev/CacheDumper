package me.uwu.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import me.uwu.utils.FastCopy;
import me.uwu.utils.FastDelete;
import me.uwu.utils.FileInfo;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Controller {
    @FXML protected void dumpThis(ActionEvent event) throws IOException {
        System.out.println("\u001B[32m" + "Started DUUUUUUMPING boi !" + "\u001B[0m");

        System.out.println("\u001B[34m" + "Cleaning temp files" + "\u001B[0m");
        FastDelete.folder(System.getenv("APPDATA")+"/CacheDumper/tempfiles");

        System.out.println("\u001B[34m" + "Copy cache" + "\u001B[0m");
        FastCopy.folder(System.getenv("APPDATA")+"/discord\\Cache",System.getenv("APPDATA")+"/CacheDumper/tempfiles");

        System.out.println("\u001B[34m" + "Filter cache copy" + "\u001B[0m");
        for(int oof = 0; oof <=4;oof++){
            FastDelete.file(System.getenv("APPDATA")+"/CacheDumper/tempfiles/data_" + oof);
        }

        File f = new File(System.getenv("APPDATA")+"/CacheDumper/tempfiles");
        ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));


        for(File fo : files) {

            //System.out.println(fo.getName());

            if (FileInfo.isPNG(fo.getAbsolutePath())) {
                System.out.println("C'est un png");
                FastCopy.file(fo.getAbsolutePath(), System.getenv("APPDATA")+"/CacheDumper/tempfiles"+"/png/"+fo.getName()+".png");
                FastDelete.file(fo.getAbsolutePath());
            } else {
                System.out.println("nope");
            }


            //System.out.println(fo.getName());

            if (FileInfo.isJPG(fo.getAbsolutePath())) {
                System.out.println("C'est un jpg");
                FastCopy.file(fo.getAbsolutePath(), System.getenv("APPDATA")+"/CacheDumper/tempfiles"+"/jpg/"+fo.getName()+".jpg");
                FastDelete.file(fo.getAbsolutePath());
            } else {
                System.out.println("nope");
            }


            //System.out.println(fo.getName());

            if (FileInfo.isGIF(fo.getAbsolutePath())) {
                System.out.println("C'est un gif");
                FastCopy.file(fo.getAbsolutePath(), System.getenv("APPDATA")+"/CacheDumper/tempfiles"+"/gif/"+fo.getName()+".gif");
                FastDelete.file(fo.getAbsolutePath());
            } else {
                System.out.println("nope");
            }


            //System.out.println(fo.getName());

            if (FileInfo.isMP4(fo.getAbsolutePath())) {
                System.out.println("C'est un mp4");
                FastCopy.file(fo.getAbsolutePath(), System.getenv("APPDATA")+"/CacheDumper/tempfiles"+"/mp4/"+fo.getName()+".mp4");
                FastDelete.file(fo.getAbsolutePath());
            } else {
                System.out.println("nope");
            }

        }
    }
}
