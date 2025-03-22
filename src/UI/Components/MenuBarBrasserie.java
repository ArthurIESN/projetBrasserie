package UI.Components;

import UI.BrasserieWindow.BrasserieWindow;
import UI.Search.SearchItemForm;

import javax.swing.*;
import java.awt.*;

public class MenuBarBrasserie {
    private JMenuBar menuBar;
    private JMenu recherche;
    private JMenuItem recherche1, recherche2, recherche3;

    public MenuBarBrasserie(BrasserieWindow brasserieWindow){
        menuBar = new JMenuBar();

        recherche = new JMenu("Recherche");

        menuBar.add(recherche);

        recherche1 = new JMenuItem("Recherche1");
        recherche2 = new JMenuItem("Recherche2");
        recherche3 = new JMenuItem("Recherche3");

        recherche.add(recherche1);
        recherche.add(recherche2);
        recherche.add(recherche3);

        recherche1.addActionListener(e ->
        {
            brasserieWindow.updateWindowContent(new SearchItemForm());
        });

        recherche2.addActionListener(e -> {
            JPanel panel2 = new JPanel();
            panel2.add(new JLabel("Recherche2 selected"));
            brasserieWindow.updateWindowContent(panel2);
        });

        recherche3.addActionListener(e -> {
            JPanel panel3 = new JPanel();
            panel3.add(new JLabel("Recherche3 selected"));
            brasserieWindow.updateWindowContent(panel3);
        });
    }

    public JMenuBar getMenuBar(){
        return menuBar;
    }
}
