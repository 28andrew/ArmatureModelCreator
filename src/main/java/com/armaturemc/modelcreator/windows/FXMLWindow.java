package com.armaturemc.modelcreator.windows;

import com.armaturemc.modelcreator.ArmatureModelCreator;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

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

    public static FXMLWindow fromArmatureMCFile(String fxmlFile, AMCWindowOption... amcWindowOptions) throws IOException {
        return fromInputStream(ArmatureModelCreator.getInstance().getClass().getResourceAsStream("/" + fxmlFile), amcWindowOptions);
    }

    public static FXMLWindow fromInputStream(InputStream inputStream, AMCWindowOption... amcWindowOptions) throws  IOException{
        return new FXMLWindow(inputStream, amcWindowOptions);
    }

    public static FXMLWindow fromExistingStage(Object fxml, Stage stage, AMCWindowOption... amcWindowOptions) throws IOException {
        FXMLWindow fxmlWindow = null;
        if (fxml instanceof String){
            fxmlWindow = fromArmatureMCFile((String) fxml, amcWindowOptions);
        }else if (fxml instanceof InputStream){
            fxmlWindow = new FXMLWindow((InputStream) fxml, amcWindowOptions);
        }
        fxmlWindow.setStage(stage);
        return fxmlWindow;
    }

    FXMLWindow(InputStream fxml, AMCWindowOption... amcWindowOptions) throws IOException {
        parent = fxmlLoader.load(fxml);
        this.amcWindowOptions = amcWindowOptions;
        if (Arrays.asList(amcWindowOptions).contains(AMCWindowOptions.AUTO_START)){
            start();
        }
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

    public FXMLLoader getFxmlLoader() {
        return fxmlLoader;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public Window getWindow(){
        return scene.getWindow();
    }
}
