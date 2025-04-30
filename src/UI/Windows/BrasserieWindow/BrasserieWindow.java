package UI.Windows.BrasserieWindow;

import javax.swing.*;
import java.awt.*;

import Controller.AppController;
import UI.Components.MenuBarBrasserie;
import UI.Login.LoginPanel;
import UI.Test.Test;
import UI.Windows.WindowManager;
import UI.Windows.WindowObserver;

public class BrasserieWindow extends JFrame implements WindowObserver
{
    private final WindowManager windowManager;
    private MenuBarBrasserie menuBarBrasserie;
    private Container container;
    private JPanel contentPanel;
    private JLabel userLabel;

    public BrasserieWindow()
    {
        super("Brasserie");

        this.windowManager = WindowManager.getInstance();
        windowManager.addObserver(this);

        setSize(1280, 720);
        setLocationRelativeTo(null);

        userLabel = new JLabel();

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
        boolean isConnected = AppController.getCurrentConnectedEmployee() != null;

        if(contentPanel != null)
        {
            container.remove(contentPanel);
        }

        if(!isConnected)
        {
            panel = new LoginPanel(this);

            userLabel.setText("Not connected");
        }
        else
        {
            userLabel.setText("Connected as: " + AppController.getCurrentConnectedEmployee().getFirstName() + " " + AppController.getCurrentConnectedEmployee().getLastName() + " (" + AppController.getCurrentConnectedEmployee().getEmployeeStatus().getLabel() + ")");
        }

        userLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        container.add(userLabel, BorderLayout.NORTH);




        contentPanel = panel;
        container.add(contentPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
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
