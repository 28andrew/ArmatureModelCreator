package com.armaturemc.modelcreator.themes;

import com.armaturemc.modelcreator.windows.DialogWindow;
import com.armaturemc.modelcreator.windows.FXMLWindow;
import javafx.collections.ObservableList;

import java.util.Observable;

/**
 * @author Andrew Tran
 */
public class DefaultTheme implements Theme{
    String cssFileInJar;

    public DefaultTheme(String cssFileInJar) {
        this.cssFileInJar = cssFileInJar;
    }

    @Override
    public ThemeSource getThemeSource() {
        return ThemeSource.INCLUDED;
    }

    @Override
    public String getPath() {
        return cssFileInJar;
    }

    @Override
    public void apply(FXMLWindow fxmlWindow) {
        apply(fxmlWindow.getScene().getStylesheets());
    }

    public void apply(ObservableList<String> list){
        list.add(cssFileInJar);
    }

    @Override
    public void apply(DialogWindow dialogWindow) {
        apply(dialogWindow.getAlert().getDialogPane().getStylesheets());
    }
}
