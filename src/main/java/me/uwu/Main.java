package me.uwu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("UwU.fxml"));
        primaryStage.setTitle("Cache Dumper by UwU#0001");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        primaryStage.setScene(new Scene(root, 518, 630));
        primaryStage.show();
        primaryStage.resizableProperty().set(false);

    }

    public static void main(String[] args) {
        System.out.println("Creating window");
        launch(args);
    }
}
