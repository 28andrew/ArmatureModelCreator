package com.armaturemc.modelcreator.windows;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Andrew Tran
 */
public interface FXMLWindowOption {
    void run(Stage stage, Scene scene, FXMLWindow fxmlWindow, Boolean firstRun);
}
