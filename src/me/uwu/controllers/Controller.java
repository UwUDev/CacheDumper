package me.uwu.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import me.uwu.Main;
import me.uwu.utils.*;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Controller {

    public TextField path;
    private final String tempPath = System.getenv("APPDATA")+"/CacheDumper/tempfiles/";

    private int png, jpg, gif, mp3, mp4, gz, zip, webm, webp, font, js, json, svg, other = 0;

    private static final Logger logger = Logger.getLogger(Controller.class);

    @FXML protected void dumpThis() throws IOException {

        String finalPath;
        logger.debug(path.getText());

        logger.debug(System.getenv("UserProfile"));

        finalPath = path.getText();
        finalPath = finalPath.replace("%appdata%",System.getenv("APPDATA"));
        finalPath = finalPath.replace("%UserProfile%",System.getenv("UserProfile"));

        logger.debug(finalPath);

        logger.info("Started DUUUUUUMPING boi !");

        logger.warn("Cleaning temp files is needed");

        logger.info("Deleting : " + tempPath);
        FastDelete.folder(tempPath);

        logger.info("Deleting : " + finalPath);
        FastDelete.folder(finalPath+"/Cache Dumper");

        logger.info("Copy cache started");
        FastCopy.folder(System.getenv("APPDATA")+"/discord\\Cache",tempPath);

        Path loggerPath = Paths.get(System.getenv("APPDATA")+"/BetterDiscord/plugins/MLV2_IMAGE_CACHE");

        if (Files.exists(loggerPath)) {
            logger.info("Found message logger");
            FastCopy.folder(System.getenv("APPDATA") + "/BetterDiscord/plugins/MLV2_IMAGE_CACHE", tempPath);
        }

        StringBuilder sb = new StringBuilder();
        logger.info("Filter cache copy");
        for(int oof = 0; oof <=3;oof++){
            sb.append(FileInfo.getLinksFromFile(tempPath + "data_" + oof));
            FastDelete.file(tempPath + "data_" + oof);
        }

        sb.append(FileInfo.getLinksFromFile(tempPath +"/index"));
        FastDelete.file(tempPath +"/index");


        File links = new File(finalPath+"/Cache Dumper/Links found.txt");

        try {
            logger.info("Generating links file");
            FileUtils.touch(links);
            FileUtils.writeStringToFile(links,sb.toString());
        } catch (IOException e) {
            logger.error("Cant generate or edit Links found.txt", e);
        }

        File f = new File(tempPath);
        ArrayList<File> files = new ArrayList<>(Arrays.asList(Objects.requireNonNull(f.listFiles())));


        for(File fo : files) {

            if (fo.getName().contains(".png")) {
                logger.info("Detected type : png");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"png/"+fo.getName());
                FastDelete.file(fo.getAbsolutePath());
                png++;
            } else

            if (fo.getName().contains(".jpg")) {
                logger.info("Detected type : jpg");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"jpg/"+fo.getName());
                FastDelete.file(fo.getAbsolutePath());
                jpg++;
            } else

            if (fo.getName().contains(".gif")) {
                logger.info("Detected type : gif");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"gif/"+fo.getName());
                FastDelete.file(fo.getAbsolutePath());
                gif++;
            } else




            if (FileInfo.isPNG(fo.getAbsolutePath())) {
                logger.info("Detected type : png");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"png/"+fo.getName()+".png");
                FastDelete.file(fo.getAbsolutePath());
                png++;
            } else

            if (FileInfo.isJPG(fo.getAbsolutePath())) {
                logger.info("Detected type : jpg");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"jpg/"+fo.getName()+".jpg");
                FastDelete.file(fo.getAbsolutePath());
                jpg++;
            } else

            if (FileInfo.isGIF(fo.getAbsolutePath())) {
                logger.info("Detected type : gif");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"gif/"+fo.getName()+".gif");
                FastDelete.file(fo.getAbsolutePath());
                gif++;
            } else

            if (FileInfo.isWEBM(fo.getAbsolutePath())) {
                logger.info("Detected type : webm");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"webm/"+fo.getName()+".webm");
                FastDelete.file(fo.getAbsolutePath());
                webm++;
            } else

            if (FileInfo.isWEBP(fo.getAbsolutePath())) {
                logger.info("Detected type : webp");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"webp/"+fo.getName()+".webp");
                FastDelete.file(fo.getAbsolutePath());
                webp++;
            } else

            if (FileInfo.isMP3(fo.getAbsolutePath())) {
                logger.info("Detected type : mp3");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"mp3/"+fo.getName()+".mp3");
                FastDelete.file(fo.getAbsolutePath());
                mp3++;
            } else

            if (FileInfo.isGZ(fo.getAbsolutePath())) {
                logger.info("Detected type : gz");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"gz/"+fo.getName()+".gz");
                FastDelete.file(fo.getAbsolutePath());
                gz++;
            } else

            if (FileInfo.isZIP(fo.getAbsolutePath())) {
                logger.info("Detected type : zip");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"zip/"+fo.getName()+".zip");
                FastDelete.file(fo.getAbsolutePath());
                zip++;
            } else

            if (FileInfo.isMP4(fo.getAbsolutePath())) {
                logger.info("Detected type : mp4");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"mp4/"+fo.getName()+".mp4");
                FastDelete.file(fo.getAbsolutePath());
                mp4++;
            } else {
                logger.info("Detected type : format inconnu :/");
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
                logger.info("Detected type : woff");
                FastCopy.file(file.getAbsolutePath(), tempPath+"fonts/"+file.getName()+".woff");
                FastDelete.file(file.getAbsolutePath());
                font++;
            } else

            if (FileInfo.isJS(file.getAbsolutePath())) {
                logger.info("Detected type : js");
                FastCopy.file(file.getAbsolutePath(), tempPath+"js/"+file.getName()+".js");
                FastDelete.file(file.getAbsolutePath());
                js++;
            } else

            if (FileInfo.isSVG(file.getAbsolutePath())) {
                logger.info("Detected type : svg");
                FastCopy.file(file.getAbsolutePath(), tempPath+"svg/"+file.getName()+".svg");
                FastDelete.file(file.getAbsolutePath());
                svg++;
            } else

            if (FileInfo.isJSON(file.getAbsolutePath())) {
                logger.info("Detected type : json");
                FastCopy.file(file.getAbsolutePath(), tempPath+"json/"+file.getName()+".json");
                FastDelete.file(file.getAbsolutePath());
                json++;
            } else {
                logger.info("Detected type : txt");
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
            ZipUtils.unzip(z.getAbsolutePath(), tempPath + "Discord update files/");
        }

        FastCopy.folder(System.getenv("APPDATA")+"/CacheDumper/tempfiles",finalPath+"/Cache Dumper");
        FastDelete.file(System.getenv("APPDATA")+"/CacheDumper/logs.txt");

        FastDelete.folder(tempPath);
        FastDelete.file(System.getenv("APPDATA")+"/CacheDumper/logs.txt");

        logger.info("Succesfully dumped");

        int total = png + jpg + gif + webm + webp + mp3 + mp4 + gz + other + js + json + svg + font;

        File stats = new File(finalPath+"/Cache Dumper/Stats.txt");

        try {
            logger.info("Generating stats file");
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
            logger.error("Cant generate or edit stats.txt", e);
        }

        Desktop.getDesktop().open(new File(finalPath+"/Cache Dumper"));

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

            logger.debug(logFile.getCanonicalPath());

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
