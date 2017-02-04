package com.armaturemc.modelcreator.windows;

import com.armaturemc.modelcreator.ArmatureModelCreator;
import javafx.scene.Scene;
import javafx.stage.*;

/**
 * @author Andrew Tran
 */
public class FXMLWindowOptions {
    public static FXMLWindowOption MAXIMIZED = (stage, scene, fxmlWindow) -> stage.setMaximized(true);
    public static FXMLWindowOption NOT_MAXIMIZED = (stage, scene, fxmlWindow) -> stage.setMaximized(false);
    public static FXMLWindowOption FOCUSED = (stage, scene, fxmlWindow) -> stage.requestFocus();
    public static FXMLWindowOption AUTO_START = (stage, scene, fxmlWindow) -> {};

    public static FXMLWindowOption name(String name){
        return (stage, scene, fxmlWindow) -> stage.setTitle(name);
    }
    public static FXMLWindowOption dimensions(Integer width, Integer height){
        return (stage, scene, fxmlWindow) -> {
            stage.setWidth(width);
            stage.setHeight(height);
        };
    }
    public static FXMLWindowOption run(WindowRunner windowRunner){
        return (stage, scene, fxmlWindow) -> windowRunner.run(stage, scene);
    }
    public static FXMLWindowOption modality(Modality modality){
        return (stage, scene, fxmlWindow) -> stage.initModality(modality);
    }
    public static FXMLWindowOption owner(Window owner){
        return (stage, scene, fxmlWindow) -> stage.initOwner(owner);
    }
    public static FXMLWindowOption css(String path){
        return (stage, scene, fxmlWindow) -> scene.getStylesheets().add(path);
    }
    public static FXMLWindowOption style(StageStyle stageStyle){
        return (stage, scene, fxmlWindow) -> stage.initStyle(stageStyle);
    }
    public static FXMLWindowOption closeAction(ClosingAction closingAction){
        return (stage, scene, fxmlWindow) -> fxmlWindow.addClosingAction(closingAction);
    }
    public static FXMLWindowOption automaticTop(ArmatureModelCreator.WindowType windowType){
        return new FXMLWindowOption() {
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
