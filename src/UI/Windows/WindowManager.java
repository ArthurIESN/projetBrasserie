package UI.Windows;

import UI.Windows.BrasserieWindow.BrasserieWindow;
import UI.Windows.SettingsWindow.SettingsWindow;
import UI.Login.LoginPanel;
import UI.Windows.BrasserieWindow.BrasserieHomePanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WindowManager implements WindowSubject
{
    private final ArrayList<WindowObserver> observers = new ArrayList<>();
    private static WindowManager instance;
    private static SettingsWindow settingsWindow;
    private static ArrayList<BrasserieWindow> windows = new ArrayList<>();

    public WindowManager()
    {
    }

    public static void showSettingsWindow()
    {
        if (settingsWindow == null)
        {
            settingsWindow = new SettingsWindow();
            settingsWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        else
        {
            settingsWindow.setVisible(true);
        }
    }

    public static void onDisconnect()
    {
        for (BrasserieWindow window : windows)
        {
            window.updateWindowContent(new LoginPanel(window));
        }
    }

    public static void onConnect()
    {
        for (BrasserieWindow window : windows)
        {
            if(isPanelDisplayed(LoginPanel.class))
            {
                window.updateWindowContent(new BrasserieHomePanel());
            }
        }
    }

    public static void addWindow()
    {
        windows.add(new BrasserieWindow());
    }

    public static void removeWindow(BrasserieWindow window)
    {
        getInstance().removeObserver(window);
        windows.remove(window);

        if (windows.isEmpty())
        {
            System.exit(0);
        }
    }

    public static void setWindowsEnable(boolean enable)
    {
        for (BrasserieWindow window : windows)
        {
            window.setEnabled(enable);
        }
    }

    public static boolean isPanelDisplayed(Class<? extends JPanel> panelClass) {
        int count = 0;

        while (count < windows.size() && !containsPanel(windows.get(count).getContentPane(), panelClass))
        {
            count++;
        }

        return count < windows.size();
    }

    private static boolean containsPanel(Component parent, Class<? extends JPanel> panelClass) {
        if (panelClass.isInstance(parent))
        {
            return true;
        }

        if (parent instanceof Container)
        {
            for (Component child : ((Container) parent).getComponents())
            {
                if (containsPanel(child, panelClass))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public static void focusWindow(Class<? extends JPanel> panelClass) {
        for (BrasserieWindow window : windows)
        {
            if (containsPanel(window.getContentPane(), panelClass))
            {
                window.toFront();
                window.requestFocus();
                return;
            }
        }
    }


    public static WindowManager getInstance()
    {
        if (instance == null)
        {
            instance = new WindowManager();
        }
        return instance;
    }

    @Override
    public void addObserver(WindowObserver observer)
    {
        observers.add(observer);
    }

    @Override
    public void removeObserver(WindowObserver observer)
    {
        observers.remove(observer);
    }

    @Override
    public void notifyThemeChanged()
    {
        for (WindowObserver observer : observers)
        {
            observer.onThemeChanged();
        }
    }
}
