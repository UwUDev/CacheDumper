package me.uwu.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import me.uwu.utils.*;
import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Objects;

public class Controller {

    public TextField path;
    private final String tempPath = System.getenv("APPDATA")+"/CacheDumper/tempfiles/";

    private int png, jpg, gif, mp3, mp4, gz, zip, webm, webp, font, js, json, svg, other = 0;


    @FXML protected void dumpThis() throws IOException {

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

        System.out.println("\u001B[32m" + "Started DUUUUUUMPING boi !" + TimeUtils.dateAndTime());



        System.out.println("Cleaning temp files" + TimeUtils.dateAndTime());
        FastDelete.folder(tempPath);
        FastDelete.folder(finalPath+"/Cache Dumper");

        System.out.println("Copy cache" + TimeUtils.dateAndTime());
        FastCopy.folder(System.getenv("APPDATA")+"/discord\\Cache",tempPath);
        FastCopy.folder(System.getenv("APPDATA")+"/BetterDiscord/plugins/MLV2_IMAGE_CACHE",tempPath);


        System.out.println("Filter cache copy" + TimeUtils.dateAndTime());
        for(int oof = 0; oof <=4;oof++){
            FastDelete.file(tempPath + "data_" + oof);
        }
        FastDelete.file(tempPath +"/index");

        File f = new File(tempPath);
        ArrayList<File> files = new ArrayList<>(Arrays.asList(Objects.requireNonNull(f.listFiles())));


        for(File fo : files) {

            if (fo.getName().contains(".png")) {
                System.out.println("C'est un png" + TimeUtils.dateAndTime());
                FastCopy.file(fo.getAbsolutePath(), tempPath+"png/"+fo.getName());
                FastDelete.file(fo.getAbsolutePath());
                png++;
            } else

            if (fo.getName().contains(".jpg")) {
                System.out.println("C'est un jpg" + TimeUtils.dateAndTime());
                FastCopy.file(fo.getAbsolutePath(), tempPath+"jpg/"+fo.getName());
                FastDelete.file(fo.getAbsolutePath());
                jpg++;
            } else

            if (fo.getName().contains(".gif")) {
                System.out.println("C'est un gif" + TimeUtils.dateAndTime());
                FastCopy.file(fo.getAbsolutePath(), tempPath+"gif/"+fo.getName());
                FastDelete.file(fo.getAbsolutePath());
                gif++;
            } else




            if (FileInfo.isPNG(fo.getAbsolutePath())) {
                System.out.println("C'est un png" + TimeUtils.dateAndTime());
                FastCopy.file(fo.getAbsolutePath(), tempPath+"png/"+fo.getName()+".png");
                FastDelete.file(fo.getAbsolutePath());
                png++;
            } else

            if (FileInfo.isJPG(fo.getAbsolutePath())) {
                System.out.println("C'est un jpg" + TimeUtils.dateAndTime());
                FastCopy.file(fo.getAbsolutePath(), tempPath+"jpg/"+fo.getName()+".jpg");
                FastDelete.file(fo.getAbsolutePath());
                jpg++;
            } else

            if (FileInfo.isGIF(fo.getAbsolutePath())) {
                System.out.println("C'est un gif" + TimeUtils.dateAndTime());
                FastCopy.file(fo.getAbsolutePath(), tempPath+"gif/"+fo.getName()+".gif");
                FastDelete.file(fo.getAbsolutePath());
                gif++;
            } else

            if (FileInfo.isWEBM(fo.getAbsolutePath())) {
                System.out.println("C'est un webm" + TimeUtils.dateAndTime());
                FastCopy.file(fo.getAbsolutePath(), tempPath+"webm/"+fo.getName()+".webm");
                FastDelete.file(fo.getAbsolutePath());
                webm++;
            } else

            if (FileInfo.isWEBP(fo.getAbsolutePath())) {
                System.out.println("C'est un webp" + TimeUtils.dateAndTime());
                FastCopy.file(fo.getAbsolutePath(), tempPath+"webp/"+fo.getName()+".webp");
                FastDelete.file(fo.getAbsolutePath());
                webp++;
            } else

            if (FileInfo.isMP3(fo.getAbsolutePath())) {
                System.out.println("C'est un mp3" + TimeUtils.dateAndTime());
                FastCopy.file(fo.getAbsolutePath(), tempPath+"mp3/"+fo.getName()+".mp3");
                FastDelete.file(fo.getAbsolutePath());
                mp3++;
            } else

            if (FileInfo.isGZ(fo.getAbsolutePath())) {
                System.out.println("C'est un gz" + TimeUtils.dateAndTime());
                FastCopy.file(fo.getAbsolutePath(), tempPath+"gz/"+fo.getName()+".gz");
                FastDelete.file(fo.getAbsolutePath());
                gz++;
            } else

            if (FileInfo.isZIP(fo.getAbsolutePath())) {
                System.out.println("C'est un zip" + TimeUtils.dateAndTime());
                FastCopy.file(fo.getAbsolutePath(), tempPath+"zip/"+fo.getName()+".zip");
                FastDelete.file(fo.getAbsolutePath());
                zip++;
            } else

            if (FileInfo.isMP4(fo.getAbsolutePath())) {
                System.out.println("C'est un mp4" + TimeUtils.dateAndTime());
                FastCopy.file(fo.getAbsolutePath(), tempPath+"mp4/"+fo.getName()+".mp4");
                FastDelete.file(fo.getAbsolutePath());
                mp4++;
            } else {
                System.out.println("C'est un format inconnu :/" + TimeUtils.dateAndTime());
                FastCopy.file(fo.getAbsolutePath(), tempPath+"unknown/"+fo.getName());
                FastDelete.file(fo.getAbsolutePath());
                other++;
            }

        }

        File gzf = new File(tempPath+"gz/");
        File txtf = new File(tempPath+"extracted/");
        ArrayList<File> gzfiles = new ArrayList<>(Arrays.asList(Objects.requireNonNull(gzf.listFiles())));

        FileUtils.forceMkdir(txtf);

        int ty = 0;

        for(File gz : gzfiles) {

            GZipUtils.unGzipFile(gz.getAbsolutePath(), tempPath+"extracted/"+ty);
            ty++;
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        File extracted = new File(tempPath+"extracted/");
        ArrayList<File> extractedfiles = new ArrayList<>(Arrays.asList(Objects.requireNonNull(extracted.listFiles())));

        for(File file : extractedfiles) {

            if (FileInfo.isWOFF(file.getAbsolutePath())) {
                System.out.println("C'est un woff" + TimeUtils.dateAndTime());
                FastCopy.file(file.getAbsolutePath(), tempPath+"fonts/"+file.getName()+".woff");
                FastDelete.file(file.getAbsolutePath());
                font++;
            } else

            if (FileInfo.isJS(file.getAbsolutePath())) {
                System.out.println("C'est un js" + TimeUtils.dateAndTime());
                FastCopy.file(file.getAbsolutePath(), tempPath+"js/"+file.getName()+".js");
                FastDelete.file(file.getAbsolutePath());
                js++;
            } else

            if (FileInfo.isSVG(file.getAbsolutePath())) {
                System.out.println("C'est un svg" + TimeUtils.dateAndTime());
                FastCopy.file(file.getAbsolutePath(), tempPath+"svg/"+file.getName()+".svg");
                FastDelete.file(file.getAbsolutePath());
                svg++;
            } else

            if (FileInfo.isJSON(file.getAbsolutePath())) {
                System.out.println("C'est un json" + TimeUtils.dateAndTime());
                FastCopy.file(file.getAbsolutePath(), tempPath+"json/"+file.getName()+".json");
                FastDelete.file(file.getAbsolutePath());
                json++;
            } else {
                System.out.println("C'est un txt" + TimeUtils.dateAndTime());
                FastCopy.file(file.getAbsolutePath(), tempPath+"txt/"+file.getName()+".txt");
                FastDelete.file(file.getAbsolutePath());
            }

        }

        FastDelete.folder(tempPath+"extracted/");

        File zipFolder = new File(tempPath+"zip/");
        FileUtils.forceMkdir(zipFolder);

        File zf = new File(tempPath+"zip/");
        ArrayList<File> zfiles = new ArrayList<>(Arrays.asList(Objects.requireNonNull(zf.listFiles())));

        for (File z : zfiles) {
            System.out.println("extracting " + z.getAbsolutePath() + TimeUtils.dateAndTime());
            ZipUtils.unzip(z.getAbsolutePath(), tempPath + "Discord update files/");
        }

        FastCopy.folder(System.getenv("APPDATA")+"/CacheDumper/tempfiles",finalPath+"/Cache Dumper");
        FastCopy.file(System.getenv("APPDATA")+"/CacheDumper/logs.txt",finalPath+"/Cache Dumper/logs.txt");
        FastDelete.file(System.getenv("APPDATA")+"/CacheDumper/logs.txt");

        FastDelete.folder(tempPath);
        FastDelete.file(System.getenv("APPDATA")+"/CacheDumper/logs.txt");

        System.out.println("Succesfully dumped" + TimeUtils.dateAndTime());

        int total = png + jpg + gif + webm + webp + mp3 + mp4 + gz + other + js + json + svg + font;

        File stats = new File(finalPath+"/Cache Dumper/Stats.txt");

        try {
            FileUtils.touch(stats);
            FileUtils.writeStringToFile(stats,
                    "Total .png files : " + png +
                            "\nTotal .jpg files : " + jpg +
                            "\nTotal .svg files : " + svg +
                            "\nTotal .gif files : " + gif +
                            "\nTotal .mp4 files : " + mp4 +
                            "\nTotal .webm files : " + webm +
                            "\nTotal .webp files : " + webp +
                            "\nTotal .mp3 files : " + mp3 +
                            "\nTotal .zip files : " + zip +
                            "\nTotal .gz files : " + gz +
                            "\nTotal .txt files : " + gz +
                            "\nTotal .js files : " + js +
                            "\nTotal .json files : " + json +
                            "\nTotal font files : " + font + " (" + font + " .woff & 0 .tff)" +
                            "\nTotal of unknown files : " + other +
                            "\n\nTotal dumped files : " + total);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Desktop.getDesktop().open(new File(finalPath+"/Cache Dumper"));

        System.setOut(baseOut);

        FastDelete.file(System.getenv("APPDATA")+"/CacheDumper/logs.txt");

        System.exit(-1);


    }

    @FXML protected void cleanThis() throws IOException, InterruptedException {

        FastDelete.folder(System.getenv("APPDATA")+"/CacheDumper");

        FileUtils.forceMkdir(new File(System.getenv("APPDATA")+"/CacheDumper"));

        Thread.sleep(1000);

        BufferedWriter writer = null;

        try {
            File logFile = new File(System.getenv("APPDATA")+"/CacheDumper/cleaned ;).txt");

            System.out.println(logFile.getCanonicalPath() + TimeUtils.dateAndTime());

            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write("Successfully cleaned backups and temporary files of cache dumper"+TimeUtils.dateAndTime());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert writer != null;
                writer.close();
            } catch (Exception ignored) {
            }
        }
        File logFile = new File(System.getenv("APPDATA")+"/CacheDumper/cleaned ;).txt");
        Desktop.getDesktop().edit(logFile);

    }

}
