package com.armaturemc.modelcreator;

import com.armaturemc.modelcreator.themes.Theme;
import com.armaturemc.modelcreator.windows.*;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    private ArrayList<FXMLWindow> windows = new ArrayList<>();
    private ArrayList<DialogWindow> dialogWindows = new ArrayList<>();
    private static FXMLWindow mainApplicationWindow, welcomeWindow;
    private WindowType topWindowType = WindowType.MAIN;
    private FXMLWindow topFXMLWindow = null;

    private Yaml yaml = new Yaml();

    private String currentThemeRawPath = "themes/light.css";
    private Boolean isPromptingShutdown = false;

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
                FXMLWindowOptions.css(currentThemeRawPath),
                FXMLWindowOptions.promptShutdown(),
                FXMLWindowOptions.automaticTop(WindowType.MAIN),
                FXMLWindowOptions.AUTO_ADD,
                FXMLWindowOptions.AUTO_START
        );
        welcomeWindow = FXMLWindow.fromArmatureMCFile(
                "welcomewindow/welcomeWindowInitial.fxml",
                FXMLWindowOptions.name("Welcome to Armature Model Creator"),
                FXMLWindowOptions.dimensions(1280, 960),
                FXMLWindowOptions.style(StageStyle.UTILITY),
                FXMLWindowOptions.modality(Modality.WINDOW_MODAL),
                FXMLWindowOptions.css("common.css"),
                FXMLWindowOptions.css(currentThemeRawPath),
                FXMLWindowOptions.owner(mainApplicationWindow.getWindow()),
                FXMLWindowOptions.promptShutdown(),
                FXMLWindowOptions.automaticTop(WindowType.WELCOME_INITIAL),
                FXMLWindowOptions.AUTO_ADD,
                FXMLWindowOptions.AUTO_START
        );
    }

    public void updateTheme(Theme theme){
        if (theme.getPath().equals(currentThemeRawPath)){
            return;
        }
        for (FXMLWindow fxmlWindow : getAllWindows()){
            fxmlWindow.getScene().getStylesheets().remove(currentThemeRawPath);
            theme.apply(fxmlWindow);
        }
        for (DialogWindow dialogWindow : getAllDialogs()){
            dialogWindow.getAlert().getDialogPane().getStylesheets().remove(currentThemeRawPath);
            theme.apply(dialogWindow);
        }
        currentThemeRawPath = theme.getPath();
    }

    public String getCurrentThemeRawPath() {
        return currentThemeRawPath;
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
        if (isPromptingShutdown){
            return;
        }
        isPromptingShutdown = true;
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
                isPromptingShutdown = false;
            }),
            DialogWindowOptions.css(currentThemeRawPath),
            DialogWindowOptions.AUTO_ADD,
            DialogWindowOptions.AUTO_START
        );
    }

    public List<DialogWindow> getAllDialogs(){
        return Collections.unmodifiableList(dialogWindows);
    }

    public void addWindow(FXMLWindow... fxmlWindows){
        for (FXMLWindow fxmlWindow : fxmlWindows){
            windows.add(fxmlWindow);
        }
    }

    public void addDialog(DialogWindow... dialogWindows){
        for (DialogWindow dialogWindow : dialogWindows){
            this.dialogWindows.add(dialogWindow);
        }
    }

    public List<FXMLWindow> getAllWindows(){
        return Collections.unmodifiableList(windows);
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

    public Yaml getYaml() {
        return yaml;
    }

    public static FXMLWindow getMainApplicationWindow() {
        return mainApplicationWindow;
    }

    public static FXMLWindow getWelcomeWindow() {
        return welcomeWindow;
    }
}
