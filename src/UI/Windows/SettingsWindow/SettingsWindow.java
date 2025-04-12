package UI.Windows.SettingsWindow;

import Environement.SystemProperties;
import UI.Windows.WindowManager;
import UI.Windows.WindowObserver;



import javax.swing.*;
import java.awt.*;

public class SettingsWindow extends JFrame implements WindowObserver {
    private final WindowManager windowManager;
    private final int width = 400;
    private final int height = 300;
    private JPanel settingsPanel;

    public SettingsWindow()
    {
        this.windowManager = WindowManager.getInstance();
        windowManager.addObserver(this);

        setTitle("Settings");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;

        JCheckBox macMenuBarCheckBox = new JCheckBox("Enable macOS Menu Bar");
        JCheckBox darkThemeCheckBox = new JCheckBox("Enable Dark Theme");
        ;

        JButton saveButton = new JButton("Save");
        saveButton.setEnabled(false);

        saveButton.addActionListener(e ->
        {
            SystemProperties.setDarkThemeEnabled(darkThemeCheckBox.isSelected());
            SystemProperties.setMacMenuBarEnabled(macMenuBarCheckBox.isSelected());

            saveButton.setEnabled(false);

            SystemProperties.applySettings();
            windowManager.notifyThemeChanged();

        });

        darkThemeCheckBox.setSelected(SystemProperties.getDarkThemeEnabled());

        darkThemeCheckBox.addActionListener(e -> saveButton.setEnabled(true));

        settingsPanel.add(darkThemeCheckBox, gbc);

        if (SystemProperties.getSystemType() == SystemProperties.SystemType.MAC) {
            gbc.gridy++;
            macMenuBarCheckBox.setSelected(SystemProperties.getMacMenuBarEnabled());

            macMenuBarCheckBox.addActionListener(e -> saveButton.setEnabled(true));

            settingsPanel.add(macMenuBarCheckBox, gbc);
        }


        gbc.gridy++;
        settingsPanel.add(saveButton, gbc);
        add(settingsPanel);
    }

    @Override
    public void onSettingsChanged()
    {
        // reload
        SwingUtilities.updateComponentTreeUI(this);
        revalidate();
        repaint();
    }
}