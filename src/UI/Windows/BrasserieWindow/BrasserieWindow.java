package UI.Windows.BrasserieWindow;

import javax.swing.*;
import java.awt.*;

import UI.Components.MenuBarBrasserie;
import UI.Test.Test;
import UI.Windows.WindowManager;
import UI.Windows.WindowObserver;

public class BrasserieWindow extends JFrame implements WindowObserver
{
    private final WindowManager windowManager;
    private MenuBarBrasserie menuBarBrasserie;
    private Container container;
    private JPanel contentPanel;

    public BrasserieWindow()
    {
        super("Brasserie");

        this.windowManager = WindowManager.getInstance();
        windowManager.addObserver(this);

        setSize(1280, 720);
        setLocationRelativeTo(null);

        container = getContentPane();
        container.setLayout(new BorderLayout());

        menuBarBrasserie = new MenuBarBrasserie(this);
        setJMenuBar(menuBarBrasserie.getMenuBar());

        updateWindowContent(new Test());

        addWindowListener(new java.awt.event.WindowAdapter()
        {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e)
            {

                WindowManager.removeWindow(BrasserieWindow.this);
                dispose();
            }
        });

        setVisible(true);
    }

    public void updateWindowContent(JPanel panel)
    {
        if(contentPanel != null)
        {
            container.remove(contentPanel);
        }

        contentPanel = panel;
        container.add(contentPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public static void main(String[] args)
    {
        new BrasserieWindow();
    }

    @Override
    public void onThemeChanged()
    {
        // reload this window
        SwingUtilities.updateComponentTreeUI(this);
        revalidate();
        repaint();
    }
}
