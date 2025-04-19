package UI.Windows;

import UI.Windows.BrasserieWindow.BrasserieWindow;
import UI.Windows.SettingsWindow.SettingsWindow;

import javax.swing.*;
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

    public static void addWindow()
    {
        windows.add(new BrasserieWindow());
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
