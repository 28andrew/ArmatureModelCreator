package com.armaturemc.modelcreator;

import com.armaturemc.modelcreator.windows.DialogWindow;
import com.armaturemc.modelcreator.windows.DialogWindowOptions;
import com.armaturemc.modelcreator.windows.FXMLWindow;
import com.armaturemc.modelcreator.windows.FXMLWindowOptions;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    private FXMLWindow mainApplicationWindow, welcomeWindow;
    private WindowType topWindowType = WindowType.MAIN;
    private FXMLWindow topFXMLWindow = null;
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
                FXMLWindowOptions.promptShutdown(),
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
                FXMLWindowOptions.promptShutdown(),
                FXMLWindowOptions.automaticTop(WindowType.WELCOME_INITIAL),
                FXMLWindowOptions.AUTO_START
        );
    }

    public void setTopWindow(WindowType windowType, FXMLWindow fxmlWindow){
        if (windowType == null){
            throw new IllegalArgumentException("windowType is null");
        }
        if (fxmlWindow == null){
            throw new IllegalArgumentException("fxmlWindow is null");
        }
        setTopWindowType(windowType);
        setTopFXMLWindow(fxmlWindow);
        System.out.println("Window Type: " + windowType);
        System.out.println("Controller Class: " + getTopFXMLWindow().getFxmlLoader().getController().getClass().getCanonicalName());
    }

    public void promptShutdown() throws IOException {
        DialogWindow.newDialogWindow(Alert.AlertType.CONFIRMATION, new ButtonType[]{
            new ButtonType("Exit", ButtonBar.ButtonData.APPLY),
            new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE)
        },
            DialogWindowOptions.title("Confirm Exit"),
            DialogWindowOptions.headerText("Are you sure you would like to exit Armature Model Creator?"),
            //DialogWindowOptions.modality(Modality.WINDOW_MODAL),
            //DialogWindowOptions.owner(getTopFXMLWindow().getWindow()),
            DialogWindowOptions.handler((buttonResult, dialogWindow) -> {
            if (buttonResult.isPresent() && buttonResult.get().getButtonData() == ButtonBar.ButtonData.APPLY){
                    shutdown();
                }
            }),
            DialogWindowOptions.AUTO_START
        );
    }

    public void shutdown(){
        System.exit(1);
    }

    public WindowType getTopWindowType() {
        return topWindowType;
    }

    void setTopWindowType(WindowType topWindowType) {
        this.topWindowType = topWindowType;
    }

    public FXMLWindow getTopFXMLWindow() {
        return this.topFXMLWindow;
    }

    void setTopFXMLWindow(FXMLWindow topFXMLWindow) {
        this.topFXMLWindow = topFXMLWindow;
    }

    public enum WindowType{
        MAIN, WELCOME_INITIAL, PROMPT_SHUTDOWN
    }
}
