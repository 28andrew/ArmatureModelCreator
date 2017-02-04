package com.armaturemc.modelcreator;

import javafx.scene.Scene;
import javafx.stage.Stage;

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
}
