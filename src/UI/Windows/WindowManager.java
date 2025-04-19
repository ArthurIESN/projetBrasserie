package UI.Windows;

import Environement.SystemProperties;
import UI.Windows.BrasserieWindow.BrasserieWindow;
import UI.Windows.SettingsWindow.SettingsWindow;
import UI.Windows.WindowObserver;

import javax.swing.*;
import java.util.ArrayList;

public class WindowManager implements WindowSubject
{
    private final ArrayList<WindowObserver> observers = new ArrayList<>();
    private static WindowManager instance;
    private static SettingsWindow settingsWindow;

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

    public static void init()
    {
        SystemProperties.applySettings();
        BrasserieWindow brasserieWindow = new BrasserieWindow();
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
