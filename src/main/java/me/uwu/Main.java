package me.uwu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Map;

public class Main extends Application {
    private static Stage pStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        setPrimaryStage(primaryStage);
        pStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("UwU.fxml"));
        pStage.setTitle("Cache Dumper by UwU#0001");
        pStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        pStage.setScene(new Scene(root, 518, 630));
        pStage.show();
        pStage.resizableProperty().set(false);

    }

    public static void main(String[] args) {
        System.out.println("Creating window");
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
        Main.pStage = pStage;
    }
}
