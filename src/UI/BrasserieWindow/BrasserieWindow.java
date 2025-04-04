package UI.BrasserieWindow;

import javax.swing.*;
import java.awt.*;

import UI.Components.JNumberField;
import UI.Components.MenuBarBrasserie;

public class BrasserieWindow extends JFrame
{
    private MenuBarBrasserie menuBarBrasserie;
    private Container container;
    private JPanel contentPanel;

    public BrasserieWindow()
    {
        super("Brasserie");

        setSize(1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        container = getContentPane();
        container.setLayout(new BorderLayout());

        menuBarBrasserie = new MenuBarBrasserie(this);
        setJMenuBar(menuBarBrasserie.getMenuBar());

        JNumberField numberField = new JNumberField(JNumberField.NumberType.FLOAT, 2);
        numberField.setMinMax(-20, 100);
        numberField.setAllowNegative(false);
        numberField.setPlaceholder("Enter a number");
        add(numberField, BorderLayout.SOUTH);

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
}
