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

    private String tempPath = System.getenv("APPDATA")+"/CacheDumper/tempfiles/";

    @FXML protected void dumpThis(ActionEvent event) throws IOException {
        System.out.println("\u001B[32m" + "Started DUUUUUUMPING boi !" + "\u001B[0m");

        System.out.println("\u001B[34m" + "Cleaning temp files" + "\u001B[0m");
        FastDelete.folder(tempPath);

        System.out.println("\u001B[34m" + "Copy cache" + "\u001B[0m");
        FastCopy.folder(System.getenv("APPDATA")+"/discord\\Cache",tempPath);
        FastCopy.folder(System.getenv("APPDATA")+"/BetterDiscord/plugins/MLV2_IMAGE_CACHE",tempPath);


        System.out.println("\u001B[34m" + "Filter cache copy" + "\u001B[0m");
        for(int oof = 0; oof <=4;oof++){
            FastDelete.file(tempPath + "data_" + oof);
        }
        FastDelete.file(tempPath +"/index");

        File f = new File(tempPath);
        ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));


        for(File fo : files) {

            if (fo.getName().contains(".png")) {
                System.out.println("C'est un png");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"png/"+fo.getName());
                FastDelete.file(fo.getAbsolutePath());
            } else

            if (fo.getName().contains(".jpg")) {
                System.out.println("C'est un jpg");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"jpg/"+fo.getName());
                FastDelete.file(fo.getAbsolutePath());
            } else

            if (fo.getName().contains(".gif")) {
                System.out.println("C'est un gif");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"gif/"+fo.getName());
                FastDelete.file(fo.getAbsolutePath());
            } else

            if (fo.getName().contains(".webm")) {
                System.out.println("C'est un webm");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"webm/"+fo.getName());
                FastDelete.file(fo.getAbsolutePath());
            } else

            if (fo.getName().contains(".mp4")) {
                System.out.println("C'est un mp4");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"mp4/"+fo.getName());
                FastDelete.file(fo.getAbsolutePath());
            } else




            if (FileInfo.isPNG(fo.getAbsolutePath())) {
                System.out.println("C'est un png");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"png/"+fo.getName()+".png");
                FastDelete.file(fo.getAbsolutePath());
            } else

            if (FileInfo.isJPG(fo.getAbsolutePath())) {
                System.out.println("C'est un jpg");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"jpg/"+fo.getName()+".jpg");
                FastDelete.file(fo.getAbsolutePath());
            } else

            if (FileInfo.isGIF(fo.getAbsolutePath())) {
                System.out.println("C'est un gif");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"gif/"+fo.getName()+".gif");
                FastDelete.file(fo.getAbsolutePath());
            } else

            if (FileInfo.isWEBM(fo.getAbsolutePath())) {
                System.out.println("C'est un webm");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"webm/"+fo.getName()+".webm");
                FastDelete.file(fo.getAbsolutePath());
            } else

            if (FileInfo.isMP4(fo.getAbsolutePath())) {
                System.out.println("C'est un mp4");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"mp4/"+fo.getName()+".mp4");
                FastDelete.file(fo.getAbsolutePath());
            } else {
                System.out.println("C'est un format inconnu :/");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"unknown/"+fo.getName());
                FastDelete.file(fo.getAbsolutePath());
            }

        }

        System.out.println("\u001B[32m" + "Succesfully dumped" + "\u001B[0m");

    }
}