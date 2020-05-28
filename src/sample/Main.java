package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Cache Dumper by UwU#0001");

        primaryStage.setScene(new Scene(root, 1270, 710));
        primaryStage.show();
        primaryStage.resizableProperty().set(false);

        

    }


    public static void main(String[] args) {
        launch(args);
    }
}
