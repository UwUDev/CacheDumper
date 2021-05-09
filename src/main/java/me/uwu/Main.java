package me.uwu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

public class Main extends Application {

    private static final Logger logger = Logger.getLogger(Main.class);

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("UwU.fxml"));
        primaryStage.setTitle("Cache Dumper by UwU#0001");
        primaryStage.setScene(new Scene(root, 518, 630));
        primaryStage.show();
        primaryStage.resizableProperty().set(false);

    }

    public static void main(String[] args) {
        logger.info("Creating window");
        launch(args);
    }
}
