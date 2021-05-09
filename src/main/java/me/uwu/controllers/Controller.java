package me.uwu.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import me.uwu.Dumper;
import me.uwu.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public ImageView settingsBtn;
    public TextField path;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        settingsBtn.setImage(new Image(Main.class.getResourceAsStream("settings.png")));
        settingsBtn.setOnMouseClicked(e -> System.out.println("Settings"));
    }

    @FXML
    protected void dumpThis() throws IOException {
        Dumper dumper = new Dumper();
        dumper.dump(path.getText());
    }

    public void cleanThis() throws IOException {
        Dumper dumper = new Dumper();
        dumper.clean();
    }
}
