package me.uwu.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import me.uwu.Dumper;
import me.uwu.Main;
import me.uwu.filter.Filter;
import me.uwu.filter.IFilter;
import me.uwu.filter.filters.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public ImageView settingsBtn;
    public TextField path;
    public CheckBox checkImages;
    public CheckBox checkVideos;
    public CheckBox checkAudios;
    public CheckBox checkLogs;
    public CheckBox checkDbs;
    public CheckBox checkOthers;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        settingsBtn.setImage(new Image(Main.class.getResourceAsStream("settings.png")));
        settingsBtn.setOnMouseClicked(e -> System.out.println("Settings"));
    }

    @FXML
    protected void dumpThis() throws IOException {
        updateFilters();
        Dumper dumper = new Dumper();
        dumper.dump(path.getText(), keepUnknown(), checkOthers.isSelected());
    }

    public void cleanThis() throws IOException {
        Dumper dumper = new Dumper();
        dumper.clean();
    }

    private void updateFilters(){
        List<IFilter> filters = new ArrayList<>();

        if (checkImages.isSelected()) {
            filters.add(new PngFilter());
            filters.add(new JpgFilter());
            filters.add(new WebpFilter());
            filters.add(new IcoFilter());
            filters.add(new GifFilter());
        }

        if (checkVideos.isSelected()){
            filters.add(new Mp4Filter());
            filters.add(new WebmFilter());
        }
        if (checkAudios.isSelected())
            filters.add(new Mp3Filter());

        if (checkDbs.isSelected())
            filters.add(new DbFilter());

        if (checkLogs.isSelected())
            filters.add(new LogFilter());

        if (checkOthers.isSelected()){
            filters.add(new GzFilter());
            filters.add(new JsFilter());
            filters.add(new LogFilter());
            filters.add(new WoffFilter());
            filters.add(new ZipFilter());
            filters.add(new JsonFilter());
        }

        Filter.filters = filters;
    }

    private boolean keepUnknown(){
        return (checkImages.isSelected() && checkVideos.isSelected() && checkAudios.isSelected() && checkLogs.isSelected() && checkDbs.isSelected() && checkOthers.isSelected());
    }
}
