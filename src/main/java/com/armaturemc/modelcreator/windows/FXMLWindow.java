package com.armaturemc.modelcreator.windows;

import com.armaturemc.modelcreator.ArmatureModelCreator;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Andrew Tran
 */
public class FXMLWindow implements AMCWindow{
    private Stage stage = new Stage();
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private Parent parent;
    private Scene scene;
    private FXMLWindowOption[] FXMLWindowOptions;
    protected HashMap<String,Object> optionStorage = new HashMap<>();
    private ArrayList<ClosingAction> closingActions = new ArrayList<>();

    public static FXMLWindow fromArmatureMCFile(String fxmlFile, FXMLWindowOption... FXMLWindowOptions) throws IOException {
        return fromInputStream(ArmatureModelCreator.getInstance().getClass().getResourceAsStream("/" + fxmlFile), FXMLWindowOptions);
    }

    public static FXMLWindow fromInputStream(InputStream inputStream, FXMLWindowOption... FXMLWindowOptions) throws  IOException{
        return new FXMLWindow(inputStream, FXMLWindowOptions);
    }

    public static FXMLWindow fromExistingStage(Object fxml, Stage stage, FXMLWindowOption... FXMLWindowOptions) throws IOException {
        FXMLWindow fxmlWindow = null;
        if (fxml instanceof String){
            fxmlWindow = fromArmatureMCFile((String) fxml, FXMLWindowOptions);
        }else if (fxml instanceof InputStream){
            fxmlWindow = new FXMLWindow((InputStream) fxml, FXMLWindowOptions);
        }
        fxmlWindow.setStage(stage);
        return fxmlWindow;
    }

    FXMLWindow(InputStream fxml, FXMLWindowOption... FXMLWindowOptions) throws IOException {
        parent = fxmlLoader.load(fxml);
        this.FXMLWindowOptions = FXMLWindowOptions;
        if (Arrays.asList(FXMLWindowOptions).contains(com.armaturemc.modelcreator.windows.FXMLWindowOptions.AUTO_START)){
            start(true);
        }
    }

    public void changeFXML(String fxml) throws IOException {
        changeFXML(ArmatureModelCreator.getInstance().getClass().getResourceAsStream("/" + fxml));
    }

    public void changeFXMLAndCSS(String fxml) throws IOException {
        changeFXML(ArmatureModelCreator.getInstance().getClass().getResourceAsStream("/" + fxml));
        runOption(com.armaturemc.modelcreator.windows.FXMLWindowOptions.css("common.css"), true);
        runOption(com.armaturemc.modelcreator.windows.FXMLWindowOptions.css(ArmatureModelCreator.getInstance().getCurrentThemeRawPath()), true);
    }

    public void changeFXML(InputStream fxml) throws IOException {
        fxmlLoader = new FXMLLoader();
        parent = fxmlLoader.load(fxml);
        start(false);
    }

    public void runOption(FXMLWindowOption fxmlWindowOption, Boolean firstRun){
        fxmlWindowOption.run(stage, scene, this, firstRun);
    }

    @Override
    public void start(Boolean firstRun) {
        scene = new Scene(parent);
        stage.setScene(scene);
        if (FXMLWindowOptions != null){
            for (FXMLWindowOption fxmlWindowOption : getFXMLWindowOptions()){
                runOption(fxmlWindowOption, firstRun);
            }
        }
        stage.show();
        FXMLWindow fxmlWindow = this;
        stage.setOnCloseRequest(event -> {
            for (ClosingAction closingAction : closingActions){
                closingAction.close(stage, scene, fxmlWindow, event);
            }
        });
    }

    public void addClosingAction(ClosingAction closingAction){
        closingActions.add(closingAction);
    }

    public FXMLWindowOption[] getFXMLWindowOptions() {
        return FXMLWindowOptions;
    }

    public void setFXMLWindowOptions(FXMLWindowOption[] FXMLWindowOptions) {
        this.FXMLWindowOptions = FXMLWindowOptions;
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

    public Object getController(){
        return fxmlLoader.getController();
    }
}
