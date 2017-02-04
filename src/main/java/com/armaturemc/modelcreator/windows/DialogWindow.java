package com.armaturemc.modelcreator.windows;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author Andrew Tran
 */
public class DialogWindow implements AMCWindow{
    Alert alert;
    DialogWindowOption[] dialogWindowOptions;
    DialogWindowHandler dialogWindowHandler;
    public static DialogWindow newDialogWindow(Alert.AlertType alertType){
        return new DialogWindow(new Alert(alertType));
    }

    public static DialogWindow newDialogWindow(Alert.AlertType alertType, ButtonType... buttonTypes){
        Alert alert = new Alert(alertType);
        alert.getButtonTypes().setAll(buttonTypes);
        return new DialogWindow(alert);
    }

    public static DialogWindow newDialogWindow(Alert.AlertType alertType, DialogWindowOption... dialogWindowOptions){
        return new DialogWindow(new Alert(alertType), dialogWindowOptions);
    }

    public static DialogWindow newDialogWindow(Alert.AlertType alertType, ButtonType[] buttonTypes, DialogWindowOption... dialogWindowOptions){
        Alert alert = new Alert(alertType);
        alert.getButtonTypes().setAll(buttonTypes);
        return new DialogWindow(alert, dialogWindowOptions);
    }

    DialogWindow(Alert alert, DialogWindowOption... dialogWindowOptions){
        this.alert = alert;
        this.dialogWindowOptions = dialogWindowOptions;
        if (Arrays.asList(dialogWindowOptions).contains(DialogWindowOptions.AUTO_START)){
            start();
        }
    }

    public Alert getAlert() {
        return alert;
    }

    public void setDialogWindowHandler(DialogWindowHandler dialogWindowHandler) {
        this.dialogWindowHandler = dialogWindowHandler;
    }

    @Override
    public void start() {
        for (DialogWindowOption dialogWindowOption : dialogWindowOptions){
            dialogWindowOption.run(getAlert(), this);
        }
        alert.getDialogPane().getStyleClass().add("all-dialog");
        Optional<ButtonType> result = alert.showAndWait();
        if (dialogWindowHandler != null){
            dialogWindowHandler.handle(result, this);
        }
    }
}
