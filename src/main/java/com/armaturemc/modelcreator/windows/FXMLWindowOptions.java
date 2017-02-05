package com.armaturemc.modelcreator.windows;

import com.armaturemc.modelcreator.ArmatureModelCreator;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.*;

import java.io.IOException;

/**
 * @author Andrew Tran
 */
public class FXMLWindowOptions {
    public static FXMLWindowOption MAXIMIZED = (stage, scene, fxmlWindow, firstRun) -> stage.setMaximized(true);
    public static FXMLWindowOption NOT_MAXIMIZED = (stage, scene, fxmlWindow, firstRun) -> stage.setMaximized(false);
    public static FXMLWindowOption FOCUSED = (stage, scene, fxmlWindow, firstRun) -> stage.requestFocus();
    public static FXMLWindowOption AUTO_START = (stage, scene, fxmlWindow, firstRun) -> {};
    public static FXMLWindowOption AUTO_ADD = (stage, scene, fxmlWindow, firstRun) -> ArmatureModelCreator.getInstance().addWindow(fxmlWindow);
    public static FXMLWindowOption name(String name){
        return (stage, scene, fxmlWindow, firstRun) -> stage.setTitle(name);
    }
    public static FXMLWindowOption dimensions(Integer width, Integer height){
        return (stage, scene, fxmlWindow, firstRun) -> {
            stage.setWidth(width);
            stage.setHeight(height);
        };
    }
    public static FXMLWindowOption run(WindowRunner windowRunner){
        return (stage, scene, fxmlWindow, firstRun) -> windowRunner.run(stage, scene);
    }
    public static FXMLWindowOption modality(Modality modality){
        return (stage, scene, fxmlWindow, firstRun) -> {
            if (firstRun){
                stage.initModality(modality);
            }
        };
    }
    public static FXMLWindowOption owner(Window owner){
        return (stage, scene, fxmlWindow, firstRun) -> {
            if (firstRun){
                stage.initOwner(owner);
            }
        };
    }
    public static FXMLWindowOption css(String path){
        return (stage, scene, fxmlWindow, firstRun) -> {
            if(firstRun){
                scene.getStylesheets().add(path);
            }
        };
    }
    public static FXMLWindowOption style(StageStyle stageStyle){
        return (stage, scene, fxmlWindow, firstRun) -> {
            if (firstRun){
                stage.initStyle(stageStyle);
            }
        };
    }
    public static FXMLWindowOption closeAction(ClosingAction closingAction){
        return (stage, scene, fxmlWindow, firstRun) -> fxmlWindow.addClosingAction(closingAction);
    }
    public static FXMLWindowOption promptShutdown(){
        return FXMLWindowOptions.closeAction((stage, scene, fxmlWindow, windowEvent) -> {
            windowEvent.consume();
            Platform.runLater(() -> {
                try {
                    ArmatureModelCreator.getInstance().promptShutdown();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
    }
    public static FXMLWindowOption automaticTop(ArmatureModelCreator.WindowType windowType){
        return new FXMLWindowOption() {
            ArmatureModelCreator.WindowType previousWindowType;
            FXMLWindow previousFXMLWindow;
            @Override
            public void run(Stage stage, Scene scene, FXMLWindow fxmlWindow, Boolean firstRun) {
                previousWindowType = ArmatureModelCreator.getInstance().getTopWindowType();
                previousFXMLWindow = ArmatureModelCreator.getInstance().getTopFXMLWindow();
                ArmatureModelCreator.getInstance().setTopWindow(windowType, fxmlWindow);
                fxmlWindow.addClosingAction((stage1, scene1, fxmlWindow1, windowEvent) -> {
                    if (previousWindowType != null && previousFXMLWindow != null){
                        ArmatureModelCreator.getInstance().setTopWindow(previousWindowType, previousFXMLWindow);
                    }
                });
            }
        };
    }
}
