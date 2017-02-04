package com.armaturemc.modelcreator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @author Andrew Tran
 */
public class ArmatureModelCreator extends Application{
    private static ArmatureModelCreator instance;

    public static ArmatureModelCreator getInstance(){
        return instance;
    }

    public static void main(String[] args){
        instance = new ArmatureModelCreator();
        launch(args);

    }

    public AMCWindow mainApplicationWindow, welcomeWindow;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainApplicationWindow = FXMLWindow.fromExistingStage("mainApplication.fxml",
                primaryStage,
                AMCWindowOptions.name("Armature Model Creator"),
                AMCWindowOptions.MAXIMIZED,
                AMCWindowOptions.AUTO_START);
    }

}
