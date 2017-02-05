package com.armaturemc.modelcreator.guis;

import com.armaturemc.modelcreator.ArmatureModelCreator;
import com.armaturemc.modelcreator.themes.DefaultTheme;
import com.armaturemc.modelcreator.themes.Theme;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * @author Andrew Tran
 */
public class WelcomeWindowInitialController {
    @FXML
    ImageView lightImage;
    @FXML
    ImageView darkImage;
    @FXML
    Button nextButton;
    public static final Theme LIGHT_THEME = new DefaultTheme("themes/light.css");
    public static final Theme DARK_THEME = new DefaultTheme("themes/dark.css");

    @FXML
    public void onLightClicked(MouseEvent mouseEvent){
        lightImage.requestFocus();
        Platform.runLater(() -> ArmatureModelCreator.getInstance().updateTheme(LIGHT_THEME));
    }
    @FXML
    public void onDarkClicked(MouseEvent mouseEvent){
        darkImage.requestFocus();
        Platform.runLater(() -> ArmatureModelCreator.getInstance().updateTheme(DARK_THEME));
    }
    public void initialize(){
        DropShadow dropShadowDark = new DropShadow(30, Color.BLACK);
        DropShadow dropShadowLight = new DropShadow(30, Color.WHITE);
        Boolean isLight = ArmatureModelCreator.getInstance().getCurrentThemeRawPath().equals("themes/light.css");
        (isLight ? lightImage : darkImage).setEffect(isLight ? dropShadowDark : dropShadowLight);
        lightImage.focusedProperty().addListener((observable, oldValue, newValue) -> lightImage.setEffect(newValue ? dropShadowDark : null));
        darkImage.focusedProperty().addListener((observable, oldValue, newValue) -> darkImage.setEffect(newValue ? dropShadowLight : null));
    }
    @FXML
    public void onButtonAction(ActionEvent actionEvent){
        System.out.println("NEXT");
    }
}
