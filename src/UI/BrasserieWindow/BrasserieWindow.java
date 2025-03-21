package UI.BrasserieWindow;

import javax.swing.*;

public class BrasserieWindow extends JFrame
{
    public BrasserieWindow()
    {
        super("Brasserie");
        setSize(1280, 720);
        // center windows
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new BrasserieWindow();
    }
}
