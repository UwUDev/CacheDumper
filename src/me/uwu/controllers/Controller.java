package me.uwu.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import me.uwu.utils.*;
import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller {

    public TextField path;
    private String tempPath = System.getenv("APPDATA")+"/CacheDumper/tempfiles/";

    private int png = 0;
    private int jpg = 0;
    private int gif = 0;
    private int mp3 = 0;
    private int mp4 = 0;
    private int gz = 0;
    private int zip = 0;
    private int webm = 0;
    private int webp = 0;
    private int other = 0;
    private int total = 0;

    @FXML
    private TextField userField;

    @FXML protected void dumpThis(ActionEvent event) throws IOException {

       /* String gzip_filepath = "C:\\temp\\test.gz";
        String decopressed_filepath = "C:\\temp\\test2.txt";

        GZipUtils.unGunzipFile(gzip_filepath, decopressed_filepath);*/

        PrintStream baseOut = System.out;

        FastDelete.file(System.getenv("APPDATA")+"/CacheDumper/logs.txt");

        File logs = new File(System.getenv("APPDATA")+"/CacheDumper/logs.txt");

        try {
            FileUtils.touch(logs);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        PrintStream out = new PrintStream(new FileOutputStream(System.getenv("APPDATA")+"/CacheDumper/logs.txt"));
        System.setOut(out);

        String finalPath;
        System.out.println(path.getText());

        System.out.println(System.getenv("UserProfile"));

        finalPath = path.getText();
        finalPath = finalPath.replace("%appdata%",System.getenv("APPDATA"));
        finalPath = finalPath.replace("%UserProfile%",System.getenv("UserProfile"));

        System.out.println(finalPath);

        System.out.println("\u001B[32m" + "Started DUUUUUUMPING boi !" + "\u001B[0m");



        System.out.println("\u001B[34m" + "Cleaning temp files" + "\u001B[0m");
        FastDelete.folder(tempPath);
        FastDelete.folder(finalPath+"/Cache Dumper");

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
                png++;
            } else

            if (fo.getName().contains(".jpg")) {
                System.out.println("C'est un jpg");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"jpg/"+fo.getName());
                FastDelete.file(fo.getAbsolutePath());
                jpg++;
            } else

            if (fo.getName().contains(".gif")) {
                System.out.println("C'est un gif");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"gif/"+fo.getName());
                FastDelete.file(fo.getAbsolutePath());
                gif++;
            } else




            if (FileInfo.isPNG(fo.getAbsolutePath())) {
                System.out.println("C'est un png");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"png/"+fo.getName()+".png");
                FastDelete.file(fo.getAbsolutePath());
                png++;
            } else

            if (FileInfo.isJPG(fo.getAbsolutePath())) {
                System.out.println("C'est un jpg");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"jpg/"+fo.getName()+".jpg");
                FastDelete.file(fo.getAbsolutePath());
                jpg++;
            } else

            if (FileInfo.isGIF(fo.getAbsolutePath())) {
                System.out.println("C'est un gif");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"gif/"+fo.getName()+".gif");
                FastDelete.file(fo.getAbsolutePath());
                gif++;
            } else

            if (FileInfo.isWEBM(fo.getAbsolutePath())) {
                System.out.println("C'est un webm");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"webm/"+fo.getName()+".webm");
                FastDelete.file(fo.getAbsolutePath());
                webm++;
            } else

            if (FileInfo.isWEBP(fo.getAbsolutePath())) {
                System.out.println("C'est un webp");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"webp/"+fo.getName()+".webp");
                FastDelete.file(fo.getAbsolutePath());
                webp++;
            } else

            if (FileInfo.isMP3(fo.getAbsolutePath())) {
                System.out.println("C'est un mp3");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"mp3/"+fo.getName()+".mp3");
                FastDelete.file(fo.getAbsolutePath());
                mp3++;
            } else

            if (FileInfo.isGZ(fo.getAbsolutePath())) {
                System.out.println("C'est un gz");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"gz/"+fo.getName()+".gz");
                FastDelete.file(fo.getAbsolutePath());
                gz++;
            } else

            if (FileInfo.isZIP(fo.getAbsolutePath())) {
                System.out.println("C'est un zip");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"zip/"+fo.getName()+".zip");
                FastDelete.file(fo.getAbsolutePath());
                zip++;
            } else

            if (FileInfo.isMP4(fo.getAbsolutePath())) {
                System.out.println("C'est un mp4");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"mp4/"+fo.getName()+".mp4");
                FastDelete.file(fo.getAbsolutePath());
                mp4++;
            } else {
                System.out.println("C'est un format inconnu :/");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"unknown/"+fo.getName());
                FastDelete.file(fo.getAbsolutePath());
                other++;
            }

        }

        File gzf = new File(tempPath+"gz/");
        File txtf = new File(tempPath+"txt/");
        ArrayList<File> gzfiles = new ArrayList<File>(Arrays.asList(gzf.listFiles()));

        FileUtils.forceMkdir(txtf);

        int ty = 0;

        for(File gz : gzfiles) {

            GZipUtils.unGzipFile(gz.getAbsolutePath(), tempPath+"txt/"+ty+".txt");
            ty++;
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        File zf = new File(tempPath+"zip/");
        ArrayList<File> zfiles = new ArrayList<File>(Arrays.asList(zf.listFiles()));

        for(File z : zfiles) {
            ZipUtils.unzip(z.getAbsolutePath(),tempPath+"Discord Code Files/");
        }

        FastCopy.folder(System.getenv("APPDATA")+"/CacheDumper/tempfiles",finalPath+"/Cache Dumper");
        FastCopy.file(System.getenv("APPDATA")+"/CacheDumper/logs.txt",finalPath+"/Cache Dumper/logs.txt");
        FastDelete.file(System.getenv("APPDATA")+"/CacheDumper/logs.txt");

        FastDelete.folder(tempPath);
        FastDelete.file(System.getenv("APPDATA")+"/CacheDumper/logs.txt");

        System.out.println("\u001B[32m" + "Succesfully dumped" + "\u001B[0m");

        total = png + jpg + gif + webm + webp + mp3 + mp4 + gz + other;

        File stats = new File(finalPath+"/Cache Dumper/Stats.txt");

        try {
            FileUtils.touch(stats);
            FileUtils.writeStringToFile(stats, "Total .png files : " + png + "\nTotal .jpg files : " + jpg + "\nTotal .gif files : " + gif + "\nTotal .mp4 files : " + mp4 + "\nTotal .webm files : " + webm +  "\nTotal .webp files : " + webp + "\nTotal .mp3 files : " + mp3 + "\nTotal .zip files : " + zip + "\nTotal .gz files : " + gz + "\nTotal .txt files : " + gz + "\nTotal of unknown files : " + other + "\n\nTotal dumped files : " + total);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Desktop.getDesktop().open(new File(finalPath+"/Cache Dumper"));

        System.setOut(baseOut);

        FastDelete.file(System.getenv("APPDATA")+"/CacheDumper/logs.txt");

        System.exit(-1);


    }
}
