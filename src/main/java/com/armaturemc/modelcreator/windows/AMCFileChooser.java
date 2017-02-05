package com.armaturemc.modelcreator.windows;

import com.armaturemc.modelcreator.ArmatureModelCreator;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * @author Andrew Tran
 */
public class AMCFileChooser {
    String title;
    FileChooser fileChooser = new FileChooser();
    FXMLWindow fxmlWindow;
    AMCFileChooser(String title, FXMLWindow fxmlWindow){
        this.title = title;
        this.fxmlWindow = fxmlWindow;
    }

    public static AMCFileChooser withTitle(String title, FXMLWindow parent){
        return new AMCFileChooser(title, parent);
    }

    public static AMCFileChooser withTitle(String title){
        return new AMCFileChooser(title, ArmatureModelCreator.getInstance().getTopFXMLWindow());
    }

    public static AMCFileChooser withTitle(String title, FXMLWindow parent, File startDir){
        AMCFileChooser amcFileChooser = new AMCFileChooser(title, parent);
        amcFileChooser.getFileChooser().setInitialDirectory(startDir);
        return amcFileChooser;
    }

    public static AMCFileChooser withTitle(String title, File startDir){
        AMCFileChooser amcFileChooser = new AMCFileChooser(title, ArmatureModelCreator.getInstance().getTopFXMLWindow());
        amcFileChooser.getFileChooser().setInitialDirectory(startDir);
        return amcFileChooser;
    }

    public static AMCFileChooser withTitle(String title, FXMLWindow parent, File startDir, String initialFileName){
        AMCFileChooser amcFileChooser = new AMCFileChooser(title, parent);
        amcFileChooser.getFileChooser().setInitialDirectory(startDir);
        amcFileChooser.getFileChooser().setInitialFileName(initialFileName);
        return amcFileChooser;
    }

    public static AMCFileChooser withTitle(String title, File startDir, String initialFileName){
        AMCFileChooser amcFileChooser = new AMCFileChooser(title, ArmatureModelCreator.getInstance().getTopFXMLWindow());
        amcFileChooser.getFileChooser().setInitialDirectory(startDir);
        amcFileChooser.getFileChooser().setInitialFileName(initialFileName);
        return amcFileChooser;
    }

    public FXMLWindow getFxmlWindow() {
        return fxmlWindow;
    }

    public void setFxmlWindow(FXMLWindow fxmlWindow) {
        this.fxmlWindow = fxmlWindow;
    }

    public String getTitle() {
        return title;
    }

    public void title(String title) {
        this.title = title;
    }

    public void extensionFilters(FileChooser.ExtensionFilter... extensionFilters){
        fileChooser.getExtensionFilters().addAll(extensionFilters);
    }

    public File openForSingleFile(){
        return fileChooser.showOpenDialog(fxmlWindow.getWindow());
    }

    public File openForMultipleFiles(){
        return fileChooser.showOpenDialog(fxmlWindow.getWindow());
    }

    public File openForSaveFile(){
        return fileChooser.showSaveDialog(fxmlWindow.getWindow());
    }

    public FileChooser getFileChooser() {
        return fileChooser;
    }
}
