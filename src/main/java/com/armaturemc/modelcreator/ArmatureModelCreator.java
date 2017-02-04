package com.armaturemc.modelcreator;

import com.armaturemc.modelcreator.windows.*;
import javafx.application.Application;
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
                FXMLWindowOptions.name("Armature Model Creator"),
                FXMLWindowOptions.MAXIMIZED,
                FXMLWindowOptions.modality(Modality.APPLICATION_MODAL),
                FXMLWindowOptions.css("common.css"),
                FXMLWindowOptions.css("themes/light.css"),
                FXMLWindowOptions.automaticTop(WindowType.MAIN),
                FXMLWindowOptions.AUTO_START
        );
        welcomeWindow = FXMLWindow.fromArmatureMCFile(
                "welcomeWindowInitial.fxml",
                FXMLWindowOptions.name("Welcome to Armature Model Creator"),
                FXMLWindowOptions.dimensions(960, 720),
                FXMLWindowOptions.style(StageStyle.UTILITY),
                FXMLWindowOptions.modality(Modality.WINDOW_MODAL),
                FXMLWindowOptions.css("common.css"),
                FXMLWindowOptions.owner(mainApplicationWindow.getWindow()),
                FXMLWindowOptions.css("themes/light.css"),
                FXMLWindowOptions.closeAction(new ClosingAction() {
                    @Override
                    public void close(Stage stage, Scene scene, FXMLWindow fxmlWindow, WindowEvent windowEvent) {
                        try {
                            promptShutdown();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }),
                FXMLWindowOptions.automaticTop(WindowType.WELCOME_INITIAL),
                FXMLWindowOptions.AUTO_START
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
                FXMLWindowOptions.name("Confirm Shutdown"),
                FXMLWindowOptions.dimensions(480, 240),
                FXMLWindowOptions.modality(Modality.WINDOW_MODAL),
                FXMLWindowOptions.css("common.css"),
                FXMLWindowOptions.owner(mainApplicationWindow.getWindow()),
                FXMLWindowOptions.css("themes/light.css"),
                FXMLWindowOptions.automaticTop(WindowType.PROMPT_SHUTDOWN),
                FXMLWindowOptions.AUTO_START
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
