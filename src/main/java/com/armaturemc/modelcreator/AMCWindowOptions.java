package com.armaturemc.modelcreator;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * @author Andrew Tran
 */
public class AMCWindowOptions {
    public static AMCWindowOption MAXIMIZED = (stage,scene) -> stage.setMaximized(true);
    public static AMCWindowOption NOT_MAXIMIZED = (stage,scene) -> stage.setMaximized(false);
    public static AMCWindowOption FOCUSED = (stage, scene) -> stage.requestFocus();
    public static AMCWindowOption AUTO_START = (stage, scene) -> {};

    public static AMCWindowOption name(String name){
        return (stage, scene) -> stage.setTitle(name);
    }
    public static AMCWindowOption dimensions(Integer width, Integer height){
        return (stage,scene) -> {
            stage.setWidth(width);
            stage.setHeight(height);
        };
    }
    public static AMCWindowOption run(WindowRunner windowRunner){
        return (stage, scene) -> windowRunner.run(stage, scene);
    }
    public static AMCWindowOption modality(Modality modality){
        return (stage, scene) -> stage.initModality(modality);
    }
    public static AMCWindowOption owner(Window owner){
        return (stage, scene) -> stage.initOwner(owner);
    }
    public static AMCWindowOption css(String path){
        return (stage, scene) -> scene.getStylesheets().add(path);
    }
}
