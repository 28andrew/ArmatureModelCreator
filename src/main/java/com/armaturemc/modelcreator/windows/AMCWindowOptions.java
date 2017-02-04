package com.armaturemc.modelcreator.windows;

import com.armaturemc.modelcreator.ArmatureModelCreator;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.*;

/**
 * @author Andrew Tran
 */
public class AMCWindowOptions {
    public static AMCWindowOption MAXIMIZED = (stage,scene, fxmlWindow) -> stage.setMaximized(true);
    public static AMCWindowOption NOT_MAXIMIZED = (stage,scene, fxmlWindow) -> stage.setMaximized(false);
    public static AMCWindowOption FOCUSED = (stage, scene, fxmlWindow) -> stage.requestFocus();
    public static AMCWindowOption AUTO_START = (stage, scene, fxmlWindow) -> {};

    public static AMCWindowOption name(String name){
        return (stage, scene, fxmlWindow) -> stage.setTitle(name);
    }
    public static AMCWindowOption dimensions(Integer width, Integer height){
        return (stage, scene, fxmlWindow) -> {
            stage.setWidth(width);
            stage.setHeight(height);
        };
    }
    public static AMCWindowOption run(WindowRunner windowRunner){
        return (stage, scene, fxmlWindow) -> windowRunner.run(stage, scene);
    }
    public static AMCWindowOption modality(Modality modality){
        return (stage, scene, fxmlWindow) -> stage.initModality(modality);
    }
    public static AMCWindowOption owner(Window owner){
        return (stage, scene, fxmlWindow) -> stage.initOwner(owner);
    }
    public static AMCWindowOption css(String path){
        return (stage, scene, fxmlWindow) -> scene.getStylesheets().add(path);
    }
    public static AMCWindowOption style(StageStyle stageStyle){
        return (stage, scene, fxmlWindow) -> stage.initStyle(stageStyle);
    }
    public static AMCWindowOption automaticTop(ArmatureModelCreator.WindowType windowType){
        return new AMCWindowOption() {
            ArmatureModelCreator.WindowType previousWindowType;
            FXMLWindow previousFXMLWindow;
            @Override
            public void run(Stage stage, Scene scene, FXMLWindow fxmlWindow) {
                previousWindowType = ArmatureModelCreator.getInstance().getTopWindowType();
                previousFXMLWindow = ArmatureModelCreator.getInstance().getTopFXMLWindow();
                ArmatureModelCreator.getInstance().setTopWindow(windowType, fxmlWindow);
                fxmlWindow.addClosingAction(new ClosingAction() {
                    @Override
                    public void close(Stage stage, Scene scene, FXMLWindow fxmlWindow, WindowEvent windowEvent) {
                        ArmatureModelCreator.getInstance().setTopWindow(previousWindowType, previousFXMLWindow);
                    }
                });
            }
        };
    }
}
