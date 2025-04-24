package UI.Windows.SettingsWindow;

import Environement.SystemProperties;
import UI.Components.GridBagLayoutHelper;

import javax.swing.*;

public class SettingsDefaultPanel extends JPanel
{
    public SettingsDefaultPanel(SettingsWindow settingsWindow)
    {
        GridBagLayoutHelper layoutHelper = new GridBagLayoutHelper();

        JCheckBox macMenuBarCheckBox = new JCheckBox("Enable macOS Menu Bar");
        JCheckBox darkThemeCheckBox = new JCheckBox("Enable Dark Theme");


        JButton saveButton = new JButton("Save");
        saveButton.setEnabled(false);

        saveButton.addActionListener(e ->
        {
            SystemProperties.setDarkThemeEnabled(darkThemeCheckBox.isSelected());
            SystemProperties.setMacMenuBarEnabled(macMenuBarCheckBox.isSelected());

            saveButton.setEnabled(false);

            SystemProperties.applySettings();
            settingsWindow.notifyThemeChanged();

        });

        darkThemeCheckBox.setSelected(SystemProperties.getDarkThemeEnabled());

        darkThemeCheckBox.addActionListener(e -> saveButton.setEnabled(true));

        layoutHelper.addField(darkThemeCheckBox);

        if (SystemProperties.getSystemType() == SystemProperties.SystemType.MAC) {
            macMenuBarCheckBox.setSelected(SystemProperties.getMacMenuBarEnabled());
            macMenuBarCheckBox.addActionListener(e -> saveButton.setEnabled(true));
            layoutHelper.addField(macMenuBarCheckBox);
        }

        layoutHelper.addField(saveButton);

        add(layoutHelper);
    }
}
