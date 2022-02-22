package me.uwu;

import me.uwu.controllers.Controller;
import me.uwu.filter.Filter;
import me.uwu.other.GrabLinks;
import me.uwu.utils.*;
import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Dumper {
    public Dumper(){}

    public static final String tempPath = System.getenv("APPDATA") + "/CacheDumper/tempfiles/";
    public static String finalPath;
    //private int png, jpg, gif, mp3, mp4, gz, zip, webm, webp, font, js, json, svg, other, trash, plugins, plconfig, themes, dataB, ico, log = 0;
    private final Map<String, Integer> stats = new HashMap<>();
    private boolean betterDiscord = false;

    public void dump(String path, boolean keepUnknown, boolean others) throws IOException {
        //setupMap();

        finalPath = path;
        finalPath = finalPath.replace("%appdata%", System.getenv("APPDATA")).replace("%UserProfile%", System.getenv("UserProfile"));
        
        FastDelete.folder(tempPath);
        
        FastDelete.folder(finalPath+"/Cache Dumper");
        
        FastCopy.folder(System.getenv("APPDATA")+"/discordcanary\\Cache",tempPath);

        Path bdPath = Paths.get(System.getenv("APPDATA") + "/BetterDiscord/");
        Path loggerPath = Paths.get(System.getenv("APPDATA") + "/BetterDiscord/plugins/MLV2_IMAGE_CACHE");

        if (Files.exists(bdPath) && others) {
            betterDiscord = true;
        }

        if (betterDiscord) {

            for (File pluginFile : GetFiles.fromFolder(bdPath + "/plugins")) {
                if (pluginFile.getName().contains(".json")) {
                    System.out.println("Detected BD plugin config");
                    FastCopy.file(pluginFile.getAbsolutePath(), tempPath + "plugins/configs/" + pluginFile.getName());
                    incrementStats("plconfig");
                } else if (pluginFile.getName().contains(".js")) {
                    System.out.println("Detected BD plugin");
                    FastCopy.file(pluginFile.getAbsolutePath(), tempPath + "plugins/" + pluginFile.getName());
                    incrementStats("plugins");
                }
                Controller.updateStats(stats);
            }

            for (File themeFile : GetFiles.fromFolder(bdPath + "/themes")) {
                System.out.println("Detected BD theme");
                FastCopy.file(themeFile.getAbsolutePath(), tempPath + "themes/" + themeFile.getName());
                incrementStats("themes");
                Controller.updateStats(stats);
            }

            FastCopy.file(bdPath + "/emote_data.json", tempPath + "json/emote_data.json");
            FastCopy.file(bdPath + "/bdstorage.json", tempPath + "json/bdstorage.json");

            if (Files.exists(loggerPath)) {
                System.out.println("Found message logger");
                FastCopy.folder(System.getenv("APPDATA") + "/BetterDiscord/plugins/MLV2_IMAGE_CACHE", tempPath);
            }
        }

        for (File file : GetFiles.fromFolder(System.getenv("APPDATA") + "/discordcanary")) {
            FastCopy.file(file.getAbsolutePath(), tempPath + "temp/" + file.getName());
        }

        Filter filter0 = new Filter(stats, GetFiles.fromFolder(tempPath + "/temp"), tempPath);
        filter0.filter(keepUnknown);

        FastDelete.folder(tempPath + "/temp");

        if (others)
            GrabLinks.toFile();

        Filter filter1 = new Filter(stats, GetFiles.fromFolder(tempPath), tempPath);
        filter1.filter(keepUnknown);

        FileUtils.forceMkdir(new File(tempPath+"extracted/"));

        int ty = 0;

        if (others) {
            try {
                for (File gz : GetFiles.fromFolder(tempPath + "gz/")) {
                    GZipUtils.unGzipFile(gz.getAbsolutePath(), tempPath + "extracted/" + ty);
                    ty++;
                }
            } catch (Exception e) {
                System.out.println("No gz");
            }
        }


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Filter filter2 = new Filter(stats, GetFiles.fromFolder(tempPath+"extracted/"), tempPath);
        filter2.filter(keepUnknown);

        FastDelete.folder(tempPath + "/extracted/");

        if (others) {
            FileUtils.forceMkdir(new File(tempPath + "zip/"));

            for (File z : GetFiles.fromFolder(tempPath + "zip/")) {
                ZipUtils.unzip(z.getAbsolutePath(), tempPath + "Discord update files/");
            }
        }

        Filter filter3 = new Filter(stats, GetFiles.fromFolder(tempPath), tempPath);
        filter3.filter(keepUnknown);

        FastCopy.folder(tempPath,finalPath+"/Cache Dumper");

        try {
            Thread.sleep(500); // for slow computer    me demande pas pourquoi je sais pas mais ca fix certains fichiers qui se deletent avant la cope :/
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //et oui je conjuge delete a la 3eme personne du pluriel mais de toutes facon qui vas lire ca a part toi ?

        FastDelete.folder(tempPath);

        System.out.println("Succesfully dumped");

        AtomicInteger total = new AtomicInteger();

        stats.forEach((k, v) -> total.addAndGet(v));

        File statsis = new File(finalPath+"/Cache Dumper/Stats.txt");

        try {
            System.out.println("Generating stats file");
            int dbQtt = 0;
            if (stats.get("dataB") != null && stats.get("ldb") != null)
                dbQtt = (stats.get("dataB") + stats.get("ldb"));
            String statsData =
                    "Total .png files : " + stats.get("png") +
                    "\nTotal .jpg files : " + stats.get("jpg") +
                    "\nTotal .svg files : " + stats.get("svg") +
                    "\nTotal .gif files : " + stats.get("gif") +
                    "\nTotal .mp4 files : " + stats.get("mp4") +
                    "\nTotal .webm files : " + stats.get("webm") +
                    "\nTotal .webp files : " + stats.get("webp") +
                    "\nTotal .mp3 files : " + stats.get("mp3") +
                    "\nTotal .zip files : " + stats.get("zip") +
                    "\nTotal trash files : " + stats.get("trash") +
                    "\nTotal .txt files : " + stats.get("gz") +
                    "\nTotal .js files : " + stats.get("js") +
                    "\nTotal databases : " + dbQtt +
                    "\nTotal .ico files : " + stats.get("ico") +
                    "\nTotal .log : " + stats.get("log") +
                    "\nTotal .json files : " + stats.get("json") +
                    "\nTotal font files : " + stats.get("font") + " (" + stats.get("font") + " .woff & 0 .tff)" +
                    "\n\nTotal plugins : " + stats.get("plugins") +
                    "\nTotal plugins configs : " + stats.get("plconfig") +
                    "\nTotal themes : " + stats.get("themes") +
                    "\n\nTotal of unknown files : " + stats.get("other") +
                    "\n\n\n\nTotal dumped files : " + total.get();
            FileUtils.touch(statsis);
            FileUtils.writeStringToFile(statsis, statsData.replace("null", "0"));
        } catch (IOException e) {
            System.out.println("Cant generate or edit stats.txt");
            e.printStackTrace();
        }

        Desktop.getDesktop().open(new File(finalPath+"/Cache Dumper"));

        FastDelete.file(System.getenv("APPDATA")+"/CacheDumper/logs.txt");

        stats.forEach((k, v) -> System.out.println(k + ": " + v));
        //System.exit(-1);
    }

    public void clean() throws IOException {
        FastDelete.folder(System.getenv("APPDATA") + "/CacheDumper");

        FileUtils.forceMkdir(new File(System.getenv("APPDATA") + "/CacheDumper"));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        BufferedWriter writer = null;

        try {
            File logFile = new File(System.getenv("APPDATA") + "/CacheDumper/cleaned ;).txt");

            System.out.println(logFile.getCanonicalPath());

            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write("Successfully cleaned backups and temporary files of cache dumper" + TimeUtils.dateAndTime());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert writer != null;
                writer.close();
            } catch (Exception ignored) {
            }
        }
        File logFile = new File(System.getenv("APPDATA") + "/CacheDumper/cleaned ;).txt");
        Desktop.getDesktop().edit(logFile);
    }

    private void setupMap(){
        stats.put("png", 0);
        stats.put("jpg", 0);
        stats.put("gif", 0);
        stats.put("mp3", 0);
        stats.put("mp4", 0);
        stats.put("gz", 0);
        stats.put("zip", 0);
        stats.put("webm", 0);
        stats.put("webp", 0);
        stats.put("font", 0);
        stats.put("js", 0);
        stats.put("json", 0);
        stats.put("svg", 0);
        stats.put("other", 0);
        stats.put("trash", 0);
        stats.put("plugins", 0);
        stats.put("plconfig", 0);
        stats.put("themes", 0);
        stats.put("dataB", 0);
        stats.put("ico", 0);
        stats.put("log", 0);
    }

    private void incrementStats(String stat){
        try {
            stats.put(stat, stats.get(stat) + 1);
        } catch (NullPointerException ignored) {
            stats.put(stat, 1);
        }
    }
}
