package com.armaturemc.modelcreator;

import com.armaturemc.modelcreator.windows.AMCWindowOptions;
import com.armaturemc.modelcreator.windows.FXMLWindow;
import javafx.application.Application;
import javafx.stage.Modality;
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

    public FXMLWindow mainApplicationWindow, welcomeWindow;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainApplicationWindow = FXMLWindow.fromExistingStage(
                "mainApplication.fxml",
                primaryStage,
                AMCWindowOptions.name("Armature Model Creator"),
                AMCWindowOptions.MAXIMIZED,
                AMCWindowOptions.modality(Modality.APPLICATION_MODAL),
                AMCWindowOptions.css("themes/light.css"),
                AMCWindowOptions.AUTO_START
        );
        welcomeWindow = FXMLWindow.fromArmatureMCFile(
                "welcomeWindow.fxml",
                AMCWindowOptions.name("Welcome to Armature Model Creator"),
                AMCWindowOptions.dimensions(640, 480),
                AMCWindowOptions.modality(Modality.WINDOW_MODAL),
                AMCWindowOptions.owner(mainApplicationWindow.getWindow()),
                AMCWindowOptions.css("themes/light.css"),
                AMCWindowOptions.AUTO_START
        );
    }

}
