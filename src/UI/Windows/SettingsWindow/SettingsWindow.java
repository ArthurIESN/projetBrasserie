package UI.Windows.SettingsWindow;

import UI.Windows.WindowManager;
import UI.Windows.WindowObserver;

import javax.swing.*;
import java.awt.*;


public class SettingsWindow extends JFrame implements WindowObserver {
    private final WindowManager windowManager;

    public SettingsWindow()
    {
        this.windowManager = WindowManager.getInstance();
        windowManager.addObserver(this);


        setTitle("Settings");
        int width = 600;
        int height = 500;
        setSize(width, height);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);


        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        container.add(new SettingsPanel(this), BorderLayout.CENTER);
    }

    public void notifyThemeChanged()
    {
        windowManager.notifyThemeChanged();
    }

    @Override
    public void onThemeChanged()
    {
        // reload
        SwingUtilities.updateComponentTreeUI(this);
        revalidate();
        repaint();
    }
}