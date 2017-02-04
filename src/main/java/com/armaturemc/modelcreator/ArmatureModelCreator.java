package com.armaturemc.modelcreator;

import com.armaturemc.modelcreator.windows.AMCWindowOption;
import com.armaturemc.modelcreator.windows.AMCWindowOptions;
import com.armaturemc.modelcreator.windows.FXMLWindow;
import com.armaturemc.modelcreator.windows.WindowRunner;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.IOException;

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

    public WindowType topWindowType = WindowType.MAIN;
    public FXMLWindow topFXMLWindow;
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Java FX Program is starting...");
        mainApplicationWindow = FXMLWindow.fromExistingStage(
                "mainApplication.fxml",
                primaryStage,
                AMCWindowOptions.name("Armature Model Creator"),
                AMCWindowOptions.MAXIMIZED,
                AMCWindowOptions.modality(Modality.APPLICATION_MODAL),
                AMCWindowOptions.css("common.css"),
                AMCWindowOptions.css("themes/light.css"),
                AMCWindowOptions.automaticTop(WindowType.MAIN),
                AMCWindowOptions.AUTO_START
        );
        welcomeWindow = FXMLWindow.fromArmatureMCFile(
                "welcomeWindowInitial.fxml",
                AMCWindowOptions.name("Welcome to Armature Model Creator"),
                AMCWindowOptions.dimensions(960, 720),
                AMCWindowOptions.style(StageStyle.UTILITY),
                AMCWindowOptions.modality(Modality.WINDOW_MODAL),
                AMCWindowOptions.css("common.css"),
                AMCWindowOptions.owner(mainApplicationWindow.getWindow()),
                AMCWindowOptions.css("themes/light.css"),
                AMCWindowOptions.run((stage, scene) -> stage.setOnCloseRequest(event -> {
                    try {
                        promptShutdown();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                })),
                AMCWindowOptions.automaticTop(WindowType.WELCOME_INITIAL),
                AMCWindowOptions.AUTO_START
        );
    }

    public void setTopWindow(WindowType windowType, FXMLWindow fxmlWindow){
        setTopWindowType(windowType);
        setTopFXMLWindow(fxmlWindow);
        System.out.println("Top Window is now: " + windowType.name());
    }

    public void promptShutdown() throws IOException {
        FXMLWindow.fromArmatureMCFile(
            "promptShutdown.fxml",
                AMCWindowOptions.name("Confirm Shutdown"),
                AMCWindowOptions.dimensions(240, 120),
                AMCWindowOptions.modality(Modality.WINDOW_MODAL),
                AMCWindowOptions.css("common.css"),
                AMCWindowOptions.owner(mainApplicationWindow.getWindow()),
                AMCWindowOptions.css("themes/light.css"),
                AMCWindowOptions.automaticTop(WindowType.PROMPT_SHUTDOWN),
                AMCWindowOptions.AUTO_START
        );
    }

    public WindowType getTopWindowType() {
        return topWindowType;
    }

    void setTopWindowType(WindowType topWindowType) {
        this.topWindowType = topWindowType;
    }

    public FXMLWindow getTopFXMLWindow() {
        return topFXMLWindow;
    }

    void setTopFXMLWindow(FXMLWindow topFXMLWindow) {
        this.topFXMLWindow = topFXMLWindow;
    }

    public enum WindowType{
        MAIN, WELCOME_INITIAL, PROMPT_SHUTDOWN
    }
}
