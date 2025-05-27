package BrasserieManagementSystem;

import Controller.AppController;
import Environement.SystemProperties;
import UI.Windows.WindowManager;

public class BrasserieManagementSystem
{
    public static void init()
    {
        SystemProperties.applySettings();
        AppController.autoConnect();
        WindowManager.loadWindows();
    }
}
