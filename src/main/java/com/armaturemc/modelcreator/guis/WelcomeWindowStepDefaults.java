package com.armaturemc.modelcreator.guis;

import com.armaturemc.modelcreator.ArmatureModelCreator;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

/**
 * @author Andrew Tran
 */
public class WelcomeWindowStepDefaults {
    @FXML
    public void onBackButtonClick(MouseEvent mouseEvent){
        try {
            ArmatureModelCreator.getInstance().getWelcomeWindow().changeFXMLAndCSS("welcomewindow/welcomeWindowInitial.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
