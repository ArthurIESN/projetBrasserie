package UI.Components;

import javax.swing.*;
import java.awt.*;

public class MenuBarBrasserie {
    private JMenuBar menuBar;
    private JMenu recherche;
    private JMenuItem recherche1, recherche2, recherche3;

    public MenuBarBrasserie(){
        menuBar = new JMenuBar();

        recherche = new JMenu("Recherche");

        menuBar.add(recherche);

        recherche1 = new JMenuItem("Recherche1");
        recherche2 = new JMenuItem("Recherche2");
        recherche3 = new JMenuItem("Recherche3");

        recherche.add(recherche1);
        recherche.add(recherche2);
        recherche.add(recherche3);
    }

    public JMenuBar getMenuBar(){
        return menuBar;
    }
}
