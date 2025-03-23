package UI.Components;

import UI.BrasserieWindow.BrasserieWindow;
import UI.Search.SearchItemForm;

import javax.swing.*;
import java.awt.*;

public class MenuBarBrasserie {
    private JMenuBar menuBar;
    private JMenu recherche;
    private final JMenuItem[] menuItems = new JMenuItem[3];

    public MenuBarBrasserie(BrasserieWindow brasserieWindow){
        menuBar = new JMenuBar();

        recherche = new JMenu("Recherche");

        menuBar.add(recherche);

        menuItems[0] = new JMenuItem("1 : Search Item");
        menuItems[1] = new JMenuItem("Recherche2");
        menuItems[2] = new JMenuItem("Recherche3");

        for (JMenuItem menuItem : menuItems)
        {
            recherche.add(menuItem);
        }

        menuItems[0].addActionListener(e ->
        {
            brasserieWindow.updateWindowContent(new SearchItemForm());
        });

        menuItems[1].addActionListener(e -> {
            JPanel panel2 = new JPanel();
            panel2.add(new JLabel("Recherche2 selected"));
            brasserieWindow.updateWindowContent(panel2);
        });

        menuItems[2].addActionListener(e -> {
            JPanel panel3 = new JPanel();
            panel3.add(new JLabel("Recherche3 selected"));
            brasserieWindow.updateWindowContent(panel3);
        });
    }

    public JMenuBar getMenuBar(){
        return menuBar;
    }
}
