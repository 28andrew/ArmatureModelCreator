package com.armaturemc.modelcreator.windows;

import javafx.scene.control.Alert;

/**
 * @author Andrew Tran
 */
public interface DialogWindowOption {
    void run(Alert alert, DialogWindow dialogWindow);
}
