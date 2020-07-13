package me.uwu;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import me.uwu.utils.FastDelete;
import me.uwu.utils.GZipUtils;
import org.apache.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main extends Application {

    public TextField path;

    @FXML
    private TextField userField;

    private static final Logger logger = Logger.getLogger(Main.class);

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("UwU.fxml"));
        primaryStage.setTitle("Cache Dumper by UwU#0001");
        primaryStage.setScene(new Scene(root, 1270, 710));
        primaryStage.show();
        primaryStage.resizableProperty().set(false);

    }


    public static void main(String[] args) {
        logger.info("Creating window");
        launch(args);
    }
}
