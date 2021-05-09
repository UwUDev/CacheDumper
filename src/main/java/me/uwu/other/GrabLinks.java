package me.uwu.other;

import me.uwu.Dumper;
import me.uwu.utils.FileInfo;
import me.uwu.utils.FastDelete;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class GrabLinks {

    private static final Logger logger = Logger.getLogger(GrabLinks.class);

    public static void toFile () throws IOException {

        StringBuilder sb = new StringBuilder();
        logger.info("Filter cache copy & grabbing links");
        for(int oof = 0; oof <=3;oof++){
            sb.append(FileInfo.getLinksFromFile(Dumper.tempPath + "data_" + oof));
            FastDelete.file(Dumper.tempPath + "data_" + oof);
        }

        sb.append(FileInfo.getLinksFromFile(Dumper.tempPath +"/index"));
        FastDelete.file(Dumper.tempPath +"/index");


        File links = new File(Dumper.finalPath+"/Cache Dumper/Links found.txt");

        try {
            logger.info("Generating links file to " + links.getAbsolutePath());
            FileUtils.touch(links);
            FileUtils.writeStringToFile(links,sb.toString());
        } catch (IOException e) {
            logger.error("Cant generate or edit Links found.txt", e);
        }

    }

}
