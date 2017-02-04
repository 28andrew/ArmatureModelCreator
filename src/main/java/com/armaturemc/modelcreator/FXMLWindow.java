package com.armaturemc.modelcreator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * @author Andrew Tran
 */
public class FXMLWindow implements AMCWindow{
    private Stage stage = new Stage();
    private final FXMLLoader fxmlLoader = new FXMLLoader();
    private Parent parent;
    private Scene scene;
    private AMCWindowOption[] amcWindowOptions;

    public static FXMLWindow fromFile(String fxmlFile, AMCWindowOption... amcWindowOptions) throws IOException {
        return new FXMLWindow(ArmatureModelCreator.getInstance().getClass().getResourceAsStream(fxmlFile), amcWindowOptions);
    }

    public FXMLWindow(InputStream fxml, AMCWindowOption... amcWindowOptions) throws IOException {
        parent = fxmlLoader.load(fxml);
        this.amcWindowOptions = amcWindowOptions;
        if (Arrays.asList(amcWindowOptions).contains(AMCWindowOptions.AUTO_START)){
            start();
        }
    }

    public static FXMLWindow fromExistingStage(Object fxml, Stage stage, AMCWindowOption... amcWindowOptions) throws IOException {
        FXMLWindow fxmlWindow = null;
        if (fxml instanceof String){
            fxmlWindow = fromFile("/" + fxml, amcWindowOptions);
        }else if (fxml instanceof InputStream){
            fxmlWindow = new FXMLWindow((InputStream) fxml, amcWindowOptions);
        }
        fxmlWindow.setStage(stage);
        return fxmlWindow;
    }

    @Override
    public void start() {
        scene = new Scene(parent);
        stage.setScene(scene);
        if (amcWindowOptions != null){
            for (AMCWindowOption amcWindowOption : getAmcWindowOptions()){
                amcWindowOption.run(stage, scene);
            }
        }
        stage.show();
    }

    public AMCWindowOption[] getAmcWindowOptions() {
        return amcWindowOptions;
    }

    public void setAmcWindowOptions(AMCWindowOption[] amcWindowOptions) {
        this.amcWindowOptions = amcWindowOptions;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
