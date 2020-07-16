package me.uwu.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import me.uwu.other.GrabLinks;
import me.uwu.utils.*;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Controller {

    public TextField path;
    public static final String tempPath = System.getenv("APPDATA") + "/CacheDumper/tempfiles/";

    private int png, jpg, gif, mp3, mp4, gz, zip, webm, webp, font, js, json, svg, other, trash, plugins, plconfig, themes, dataB, ico, log = 0;
    private boolean betterDiscord = false;

    private static final Logger logger = Logger.getLogger(Controller.class);

    @FXML
    protected void dumpThis() throws IOException, InterruptedException {

        String finalPath;
        logger.debug(path.getText());

        logger.debug(System.getenv("UserProfile"));

        finalPath = path.getText();
        finalPath = finalPath.replace("%appdata%", System.getenv("APPDATA")).replace("%UserProfile%", System.getenv("UserProfile"));

        logger.debug(finalPath);

        logger.info("Started DUUUUUUMPING boi !");

        logger.warn("Cleaning temp files is needed");

        logger.info("Deleting : " + tempPath);
        FastDelete.folder(tempPath);

        logger.info("Deleting : " + finalPath);
        FastDelete.folder(finalPath+"/Cache Dumper");

        logger.info("Copy cache started");
        FastCopy.folder(System.getenv("APPDATA")+"/discord\\Cache",tempPath);

        Path bdPath = Paths.get(System.getenv("APPDATA") + "/BetterDiscord/");
        Path loggerPath = Paths.get(System.getenv("APPDATA") + "/BetterDiscord/plugins/MLV2_IMAGE_CACHE");

        if (Files.exists(bdPath)) {
            betterDiscord = true;
        }

        if (betterDiscord) {

            for (File pl : GetFiles.fromFolder(bdPath.toString() + "/plugins")) {
                if (pl.getName().contains(".json")) {
                    logger.info("Detected BD plugin config");
                    FastCopy.file(pl.getAbsolutePath(), tempPath + "plugins/configs/" + pl.getName());
                    plconfig++;
                } else if (pl.getName().contains(".js")) {
                    logger.info("Detected BD plugin");
                    FastCopy.file(pl.getAbsolutePath(), tempPath + "plugins/" + pl.getName());
                    plugins++;
                }
            }

            for (File th : GetFiles.fromFolder(bdPath.toString() + "/themes")) {
                logger.info("Detected BD theme");
                FastCopy.file(th.getAbsolutePath(), tempPath + "themes/" + th.getName());
                themes++;
            }

            FastCopy.file(bdPath.toString() + "/emote_data.json", tempPath + "json/emote_data.json");
            FastCopy.file(bdPath.toString() + "/bdstorage.json", tempPath + "json/bdstorage.json");

            /*if (Files.exists(loggerPath)) {
                logger.info("Found message logger");
                FastCopy.folder(System.getenv("APPDATA") + "/BetterDiscord/plugins/MLV2_IMAGE_CACHE", finalPath + "/Other Loggers Plugins");
            }*/
        }

        for (File ff : GetFiles.fromFolder(System.getenv("APPDATA") + "/discord")) {
            FastCopy.file(ff.getAbsolutePath(), tempPath + "temp/" + ff.getName());
        }

        for (File f1 : GetFiles.fromFolder(tempPath + "/temp")) {
            if (FileInfo.isDB(f1.getAbsolutePath())) {
                logger.info("Detected Database");
                FastCopy.file(f1.getAbsolutePath(), tempPath + "database/" + f1.getName() + ".db3");
                FastDelete.file(f1.getAbsolutePath());
                dataB++;
            } else if (f1.getName().contains(".ico")) {
                logger.info("Detected type : ico");
                FastCopy.file(f1.getAbsolutePath(), tempPath + "ico/" + f1.getName());
                FastDelete.file(f1.getAbsolutePath());
                ico++;
            } else if (f1.getName().contains(".png")) {
                logger.info("Detected type : png");
                FastCopy.file(f1.getAbsolutePath(), tempPath + "png/" + f1.getName());
                FastDelete.file(f1.getAbsolutePath());
                png++;
            } else if (f1.getName().contains(".json")) {
                logger.info("Detected type : json");
                FastCopy.file(f1.getAbsolutePath(), tempPath + "json/" + f1.getName());
                FastDelete.file(f1.getAbsolutePath());
                json++;
            } else if (f1.getName().contains(".tmp")) {
                logger.info("Detected type : json");
                FastCopy.file(f1.getAbsolutePath(), tempPath + "json/" + f1.getName());
                FastDelete.file(f1.getAbsolutePath());
                json++;
            } else if (f1.getName().contains(".log")) {
                logger.info("Detected type : log");
                FastCopy.file(f1.getAbsolutePath(), tempPath + "log/" + f1.getName());
                FastDelete.file(f1.getAbsolutePath());
                log++;
            } else if (FileInfo.isJSON(f1.getAbsolutePath())) {
                logger.info("Detected type : json");
                FastCopy.file(f1.getAbsolutePath(), tempPath + "json/" + f1.getName() + ".json");
                FastDelete.file(f1.getAbsolutePath());
                json++;
            } else {
                logger.info("Unable to fine what kind of file it's :/");
                FastCopy.file(f1.getAbsolutePath(), tempPath+"unknown/"+f1.getName());
                FastDelete.file(f1.getAbsolutePath());
                other++;
            }
        }

        FastDelete.folder(tempPath + "/temp");

        GrabLinks.toFile();

        for(File fo : GetFiles.fromFolder(tempPath)) {

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
            } else

            if (FileInfo.isTrashFile(fo.getPath())) {
                logger.info("Detected trash file");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"Intentional trash files/"+fo.getName());
                FastDelete.file(fo.getAbsolutePath());
                trash++;
            } else {
                logger.info("Unable to fine what kind of file it's :/");
                FastCopy.file(fo.getAbsolutePath(), tempPath+"unknown/"+fo.getName());
                FastDelete.file(fo.getAbsolutePath());
                other++;
            }

        }

        FileUtils.forceMkdir(new File(tempPath+"extracted/"));

        int ty = 0;

        for(File gz : GetFiles.fromFolder(tempPath+"gz/")) {

            GZipUtils.unGzipFile(gz.getAbsolutePath(), tempPath+"extracted/"+ty);
            ty++;
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(File file : GetFiles.fromFolder(tempPath+"extracted/")) {

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

        FileUtils.forceMkdir( new File(tempPath+"zip/"));

        for (File z : GetFiles.fromFolder(tempPath+"zip/")) {
            ZipUtils.unzip(z.getAbsolutePath(), tempPath + "Discord update files/");
        }

        FastCopy.folder(System.getenv("APPDATA")+"/CacheDumper/tempfiles",finalPath+"/Cache Dumper");
        FastDelete.file(System.getenv("APPDATA")+"/CacheDumper/logs.txt");

        FastDelete.folder(tempPath);
        FastDelete.file(System.getenv("APPDATA")+"/CacheDumper/logs.txt");

        logger.info("Succesfully dumped");

        int total = png + jpg + gif + webm + webp + mp3 + mp4 + gz + other + js + json + svg + font + trash + plugins + plconfig + themes + dataB + ico + log;

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
                            "\nTotal trash files : " + trash +
                            "\nTotal .txt files : " + gz +
                            "\nTotal .js files : " + js +
                            "\nTotal databases : " + dataB +
                            "\nTotal .ico files : " + ico +
                            "\nTotal .log : " + log +
                            "\nTotal .json files : " + json +
                            "\nTotal font files : " + font + " (" + font + " .woff & 0 .tff)" +
                            "\n\nTotal plugins : " + plugins +
                            "\nTotal plugins configs : " + plconfig +
                            "\nTotal themes : " + themes +
                            "\n\nTotal of unknown files : " + other +
                            "\n\n\n\nTotal dumped files : " + total);
        } catch (IOException e) {
            logger.error("Cant generate or edit stats.txt", e);
        }

        Desktop.getDesktop().open(new File(finalPath+"/Cache Dumper"));

        FastDelete.file(System.getenv("APPDATA")+"/CacheDumper/logs.txt");

        System.exit(-1);


    }

      public void cleanThis() throws IOException, InterruptedException {
          FastDelete.folder(System.getenv("APPDATA")+"/CacheDumper");

          FileUtils.forceMkdir(new File(System.getenv("APPDATA")+"/CacheDumper"));

          Thread.sleep(1000);

          BufferedWriter writer = null;

          try {
              File logFile = new File(System.getenv("APPDATA")+"/CacheDumper/cleaned ;).txt");

              logger.debug(logFile.getCanonicalPath());

              writer = new BufferedWriter(new FileWriter(logFile));
              writer.write("Successfully cleaned backups and temporary files of cache dumper"+ TimeUtils.dateAndTime());
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
