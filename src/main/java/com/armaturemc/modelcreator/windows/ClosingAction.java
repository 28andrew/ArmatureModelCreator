package com.armaturemc.modelcreator.windows;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author Andrew Tran
 */
public interface ClosingAction {
    void close(Stage stage, Scene scene, FXMLWindow fxmlWindow, WindowEvent windowEvent);
}
