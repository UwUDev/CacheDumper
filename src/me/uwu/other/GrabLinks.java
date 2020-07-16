package me.uwu.other;

import me.uwu.controllers.Controller;
import me.uwu.utils.FastDelete;
import me.uwu.utils.FileInfo;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class GrabLinks {

    private static final Logger logger = Logger.getLogger(GrabLinks.class);

    public static void toFile () throws IOException {

        StringBuilder sb = new StringBuilder();
        logger.info("Filter cache copy");
        for(int oof = 0; oof <=3;oof++){
            sb.append(FileInfo.getLinksFromFile(Controller.tempPath + "data_" + oof));
            FastDelete.file(Controller.tempPath + "data_" + oof);
        }

        sb.append(FileInfo.getLinksFromFile(Controller.tempPath +"/index"));
        FastDelete.file(Controller.tempPath +"/index");


        File links = new File(Controller.tempPath+"/Cache Dumper/Links found.txt");

        try {
            logger.info("Generating links file");
            FileUtils.touch(links);
            FileUtils.writeStringToFile(links,sb.toString());
        } catch (IOException e) {
            logger.error("Cant generate or edit Links found.txt", e);
        }

    }

}
