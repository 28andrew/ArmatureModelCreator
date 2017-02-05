package com.armaturemc.modelcreator.windows;

import com.armaturemc.modelcreator.ArmatureModelCreator;
import javafx.stage.DirectoryChooser;

import java.io.File;

/**
 * @author Andrew Tran
 */
public class AMCDirectoryChooser {
    String title;
    FXMLWindow fxmlWindow;
    DirectoryChooser directoryChooser = new DirectoryChooser();
    AMCDirectoryChooser(String title, FXMLWindow fxmlWindow){
        this.title = title;
        this.fxmlWindow = fxmlWindow;
    }


    public static AMCDirectoryChooser withTitle(String title, FXMLWindow parent){
        return new AMCDirectoryChooser(title, parent);
    }

    public static AMCDirectoryChooser withTitle(String title){
        return new AMCDirectoryChooser(title, ArmatureModelCreator.getInstance().getTopFXMLWindow());
    }


    public static AMCDirectoryChooser withTitle(String title, FXMLWindow parent, File startDir){
        AMCDirectoryChooser amcDirectoryChooser = new AMCDirectoryChooser(title, parent);
        amcDirectoryChooser.getDirectoryChooser().setInitialDirectory(startDir);
        return amcDirectoryChooser;
    }

    public static AMCDirectoryChooser withTitle(String title, File startDir){
        AMCDirectoryChooser amcDirectoryChooser = new AMCDirectoryChooser(title, ArmatureModelCreator.getInstance().getTopFXMLWindow());
        amcDirectoryChooser.getDirectoryChooser().setInitialDirectory(startDir);
        return amcDirectoryChooser;
    }

    public File openForDirectory(){
        return directoryChooser.showDialog(fxmlWindow.getWindow());
    }


    public DirectoryChooser getDirectoryChooser() {
        return directoryChooser;
    }

    public String getTitle() {
        return title;
    }

    public void title(String title) {
        this.title = title;
    }

    public FXMLWindow getFxmlWindow() {
        return fxmlWindow;
    }

    public void setFxmlWindow(FXMLWindow fxmlWindow) {
        this.fxmlWindow = fxmlWindow;
    }
}
