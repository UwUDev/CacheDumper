package me.uwu.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class WaitingController implements Initializable {

    public Label title;
    public Label stats1;
    public Label stats2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stats1.setText("Databases: 0\nGzip: 0\njpg: 0\njson: 0\nmp3: 0\npng: 0\nwebm: 0\nwoff: 0");
        stats2.setText("gif: 0\nico 0\njs: 0\nlog: 0\nmp4: 0\nsvg: 0\nwebp: 0\nzip: 0");
    }

    public void cancel() {
        try {
            //noinspection deprecation
            Controller.dumpThread.stop(); //needed
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        Controller.dialog.close();
    }
}
