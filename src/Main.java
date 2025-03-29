import UI.BrasserieWindow.BrasserieWindow;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;


public class Main {
    public static void main(String[] args)
    {

        if (System.getProperty("os.name").toLowerCase().contains("mac"))
        {
            System.setProperty("apple.awt.application.appearance", "system");
            FlatMacDarkLaf.setup();
        } else
        {
            FlatDarkLaf.setup();
        }


        BrasserieWindow brasserieWindow = new BrasserieWindow();
    }
}