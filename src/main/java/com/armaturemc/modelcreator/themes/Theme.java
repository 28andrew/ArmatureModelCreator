package com.armaturemc.modelcreator.themes;

import com.armaturemc.modelcreator.windows.DialogWindow;
import com.armaturemc.modelcreator.windows.FXMLWindow;

/**
 * @author Andrew Tran
 */
public interface Theme {
    ThemeSource getThemeSource();
    String getPath();
    void apply(FXMLWindow fxmlWindow);
    void apply(DialogWindow dialogWindow);
}
