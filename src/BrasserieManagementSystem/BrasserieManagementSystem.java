package BrasserieManagementSystem;

import Environement.SystemProperties;
import UI.Windows.WindowManager;

public class BrasserieManagementSystem
{
    public static void init()
    {
        SystemProperties.applySettings();
        WindowManager.addWindow();
    }
}
