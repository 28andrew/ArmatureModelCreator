package com.armaturemc.modelcreator.guis;

import com.armaturemc.modelcreator.ArmatureModelCreator;
import com.armaturemc.modelcreator.themes.DefaultTheme;
import com.armaturemc.modelcreator.themes.Theme;
import javafx.application.Platform;
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

    public static final Theme LIGHT_THEME = new DefaultTheme("themes/light.css");
    public static final Theme DARK_THEME = new DefaultTheme("themes/dark.css");

    @FXML
    public void onLightClicked(MouseEvent mouseEvent){
        Platform.runLater(() -> ArmatureModelCreator.getInstance().updateTheme(LIGHT_THEME));
    }
    @FXML
    public void onDarkClicked(MouseEvent mouseEvent){
        Platform.runLater(() -> ArmatureModelCreator.getInstance().updateTheme(DARK_THEME));
    }
}
