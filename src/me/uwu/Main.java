package me.uwu;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("uwu.fxml"));
        primaryStage.setTitle("Cache Dumper by UwU#0001");
        primaryStage.setScene(new Scene(root, 1270, 710));
        primaryStage.show();
        primaryStage.resizableProperty().set(false);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
