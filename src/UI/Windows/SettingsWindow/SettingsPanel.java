package UI.Windows.SettingsWindow;

import Environement.SystemProperties;
import UI.Components.GridBagLayoutHelper;

import javax.swing.*;


public class SettingsPanel extends JPanel
{
    public SettingsPanel(SettingsWindow settingsWindow)
    {
        // Create the menu bar
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("General", new SettingsDefaultPanel(settingsWindow));
        tabbedPane.addTab("Database", new SettingsDatabasePanel(settingsWindow));

        add(tabbedPane);



    }
}
