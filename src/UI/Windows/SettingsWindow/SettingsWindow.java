package UI.Windows.SettingsWindow;

import UI.Windows.WindowManager;
import UI.Windows.WindowObserver;

import javax.swing.*;
import java.awt.*;


public class SettingsWindow extends JFrame implements WindowObserver {
    private final WindowManager windowManager;
    private final int width = 400;
    private final int height = 300;
    private final Container container;

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


        container = getContentPane();
        container.setLayout(new BorderLayout());

        container.add(new SettingsPanel(this), BorderLayout.CENTER);
    }

    public void notifyThemeChanged()
    {
        windowManager.notifyThemeChanged();
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