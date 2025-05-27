package UI.Windows.SettingsWindow;

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
