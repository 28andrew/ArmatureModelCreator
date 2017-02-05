package com.armaturemc.modelcreator.guis;

import com.armaturemc.modelcreator.ArmatureModelCreator;
import com.armaturemc.modelcreator.windows.AMCDirectoryChooser;
import com.armaturemc.modelcreator.windows.AMCFileChooser;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;

/**
 * @author Andrew Tran
 */
public class WelcomeWindowStepDefaults {
    @FXML
    TextField minecraftInstallationTextField;
    @FXML
    TextField workspaceDirectoryTextField;
    @FXML
    public void onSelectDirectoryMinecraftInstall(MouseEvent mouseEvent){
        File directory = AMCDirectoryChooser
                .withTitle("Select Minecraft Installation Directory")
                .openForDirectory();
        if (directory != null){
            minecraftInstallationTextField.setText(directory.getAbsolutePath());
        }
    }

    @FXML
    public void onSelectWorkspaceDirectory(MouseEvent mouseEvent){
        File directory = AMCDirectoryChooser
                .withTitle("Select Workspace Directory")
                .openForDirectory();
        if (directory != null){
            workspaceDirectoryTextField.setText(directory.getAbsolutePath());
        }
    }

    @FXML
    public void onBackButtonClick(MouseEvent mouseEvent){
        try {
            ArmatureModelCreator.getInstance().getWelcomeWindow().changeFXMLAndCSS("welcomewindow/welcomeWindowInitial.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
