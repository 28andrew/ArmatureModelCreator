package com.armaturemc.modelcreator.guis;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * @author Andrew Tran
 */
public class WelcomeWindowInitialController {
    @FXML
    ImageView lightImage;
    @FXML
    ImageView darkImage;

    @FXML
    public void onLightClicked(MouseEvent mouseEvent){
        System.out.println("LIGHT");
    }
    @FXML
    public void onDarkClicked(MouseEvent mouseEvent){
        System.out.println("DARK");
    }
}
