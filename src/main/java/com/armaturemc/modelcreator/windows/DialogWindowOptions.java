package com.armaturemc.modelcreator.windows;

import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 * @author Andrew Tran
 */
public class DialogWindowOptions {
    public static DialogWindowOption title(String title){
        return (alert, dialogWindow) -> alert.setTitle(title);
    }

    public static DialogWindowOption headerText(String headerText){
        return (alert, dialogWindow) -> alert.setHeaderText(headerText);
    }

    public static DialogWindowOption contentText(String contentText){
        return (alert, dialogWindow) -> alert.setContentText(contentText);
    }

    public static DialogWindowOption handler(DialogWindowHandler dialogWindowHandler){
        return (alert, dialogWindow) -> dialogWindow.setDialogWindowHandler(dialogWindowHandler);
    }

    public static DialogWindowOption stageStyle(StageStyle stageStyle){
        return (alert, dialogWindow) -> alert.initStyle(stageStyle);
    }

    public static DialogWindowOption modality(Modality modality){
        return (alert, dialogWindow) -> alert.initModality(modality);
    }

    public static DialogWindowOption owner(Window owner){
        return (alert, dialogWindow) -> {
            if (owner != null){
                alert.initOwner(owner);
            }
        };
    }

    public static DialogWindowOption AUTO_START = (alert, dialogWindow) -> {};
}
