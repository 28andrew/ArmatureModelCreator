package com.armaturemc.modelcreator.windows;

import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * @author Andrew Tran
 */
public interface DialogWindowHandler {
    void handle(Optional<ButtonType> result, DialogWindow dialogWindow);
}
