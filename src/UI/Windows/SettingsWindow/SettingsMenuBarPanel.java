package UI.Windows.SettingsWindow;

import javax.swing.*;

public class SettingsMenuBarPanel extends JPanel
{

    public SettingsMenuBarPanel(SettingsWindow settingsWindow) {
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("General", new SettingsPanel(settingsWindow));
        tabbedPane.addTab("Database", new JLabel("test database"));

        add(tabbedPane);
    }
}
