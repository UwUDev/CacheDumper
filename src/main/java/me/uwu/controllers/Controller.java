package me.uwu.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import me.uwu.Dumper;
import me.uwu.Main;
import me.uwu.filter.Filter;
import me.uwu.filter.IFilter;
import me.uwu.filter.filters.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public static Stage dialog;
    private static Label labelStats1;
    private static Label labelStats2;
    public static Thread dumpThread;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //noinspection ConstantConditions
        settingsBtn.setImage(new Image(Main.class.getResourceAsStream("settings.png")));
        settingsBtn.setOnMouseClicked(e -> {
            System.out.println("Settings");
            try {
                openPopup();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }

    @FXML
    protected void dumpThis() throws IOException {
        openPopup();
        dumpThread = new Thread(() -> {
            updateFilters();
            Dumper dumper = new Dumper();
            try {
                dumper.dump(path.getText(), keepUnknown(), checkOthers.isSelected());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> dialog.close());
        });
        dumpThread.start();
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

        if (checkDbs.isSelected()) {
            filters.add(new DbFilter());
            filters.add(new LdbFilter());
        }

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

    public void openPopup() throws IOException {
        dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.initOwner(Main.getPrimaryStage());
        URL url = Main.class.getResource("UwUwait.fxml");
        //noinspection ConstantConditions
        System.out.println(url.getFile());
        Parent root = FXMLLoader.load(url);
        Label label = new Label("0");
        label.setLayoutX(50);
        Scene scene = new Scene(root, 379, 488);
        dialog.setTitle("Backing up...");
        dialog.setScene(scene);
        root.getChildrenUnmodifiable().forEach(System.out::println);
        labelStats1 = (Label) scene.lookup("#stats1");
        labelStats2 = (Label) scene.lookup("#stats2");

        System.out.println(labelStats1.getText());
        dialog.getScene().getStylesheets().add(String.valueOf(Main.class.getResource("style.css")));
        dialog.setResizable(false);

        dialog.show();
    }

    public static void updateStats(Map<String, Integer> stats) {
        int dbQtt = 0;
        if (stats.get("dataB") != null && stats.get("ldb") != null)
            dbQtt = (stats.get("dataB") + stats.get("ldb"));
        int finalDbQtt = dbQtt;
        Platform.runLater(() -> {
            labelStats1.setText("Databases: " + finalDbQtt + "\n" +
                            "Gzip: " + stats.get("gz") + "\n" +
                            "jpg: " + stats.get("jpg") + "\n" +
                            "json: " + stats.get("json") + "\n" +
                            "mp3: " + stats.get("mp3") + "\n" +
                            "png: " + stats.get("png") + "\n" +
                            "webm: " + stats.get("webm") + "\n" +
                            "woff: " + stats.get("woff")
            );

            labelStats2.setText("gif: " + stats.get("gif") + "\n" +
                            "ico: " + stats.get("ico") + "\n" +
                            "js: " + stats.get("js") + "\n" +
                            "log: " + stats.get("log") + "\n" +
                            "mp4: " + stats.get("mp4") + "\n" +
                            "svg: " + stats.get("svg") + "\n" +
                            "webp: " + stats.get("webp") + "\n" +
                            "zip: " + stats.get("zip")
            );

            labelStats1.setText(labelStats1.getText().replace("null", "0"));
            labelStats2.setText(labelStats2.getText().replace("null", "0"));
        });
    }
}
