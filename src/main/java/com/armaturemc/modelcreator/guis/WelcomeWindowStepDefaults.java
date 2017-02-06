package com.armaturemc.modelcreator.guis;

import com.armaturemc.modelcreator.ArmatureModelCreator;
import com.armaturemc.modelcreator.windows.AMCDirectoryChooser;
import com.armaturemc.modelcreator.windows.AMCFileChooser;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.apache.commons.lang3.SystemUtils;

import javax.swing.filechooser.FileSystemView;
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

    public void initialize(){
        if (SystemUtils.IS_OS_WINDOWS){
            String defaultMinecraftInstall = System.getenv("APPDATA") + "/.minecraft";
            defaultMinecraftInstall = defaultMinecraftInstall.replace('\\','/');
            minecraftInstallationTextField.setText(defaultMinecraftInstall);

            String defaultWorkspaceFolderPath = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "/Armature Workspace/";
            defaultWorkspaceFolderPath = defaultWorkspaceFolderPath.replace('\\', '/');
            workspaceDirectoryTextField.setText(defaultWorkspaceFolderPath);
        }else if (SystemUtils.IS_OS_MAC_OSX){
            String defaultMinecraftInstall = System.getProperty("user.home");
            defaultMinecraftInstall += "/Library/Application Support/Minecraft";
            minecraftInstallationTextField.setText(defaultMinecraftInstall);

            workspaceDirectoryTextField.setText(System.getProperty("user.home") + "/AMC Workspace/");
        }else if (SystemUtils.IS_OS_UNIX){
            String defaultMinecraftInstall = System.getProperty("user.home") + "/.minecraft";
            minecraftInstallationTextField.setText(defaultMinecraftInstall);

            workspaceDirectoryTextField.setText(System.getProperty("user.home") + "/AMC Workspace/");
        }
    }

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
