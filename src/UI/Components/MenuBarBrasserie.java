package UI.Components;

import UI.BrasserieWindow.BrasserieWindow;
import UI.Process.CreateProcessPanel;
import UI.Process.ProcessPanel;
import UI.Search.SearchDocumentWithEventForm;
import UI.Search.SearchItemForm;
import UI.Search.SearchPaymentForm;

import javax.swing.*;
import java.awt.*;

public class MenuBarBrasserie {
    private JMenuBar menuBar;
    private JMenu brasserieMenu;
    private JMenu searchMenu;
    private final JMenuItem[] searchItems = new JMenuItem[3];
    private final JMenuItem[] crudItem = new JMenuItem[2];

    public MenuBarBrasserie(BrasserieWindow brasserieWindow){
        menuBar = new JMenuBar();

        brasserieMenu = new JMenu("Brasserie");

        menuBar.add(brasserieMenu);

        JMenuItem quitItem = new JMenuItem("Quit");

        brasserieMenu.add(quitItem);

        quitItem.addActionListener(e -> {
            System.exit(0);
        });


        searchMenu = new JMenu("Searchs");

        menuBar.add(searchMenu);

        searchItems[0] = new JMenuItem("1 : Search Item");
        searchItems[1] = new JMenuItem("Recherche2");
        searchItems[2] = new JMenuItem("3 : Search Payment");

        for (JMenuItem menuItem : searchItems)
        {
            searchMenu.add(menuItem);
        }

        searchItems[0].addActionListener(e ->
        {
            brasserieWindow.updateWindowContent(new SearchItemForm());
        });

        searchItems[1].addActionListener(e -> {
            JPanel panel2 = new JPanel();
            panel2.add(new JLabel("Recherche2 selected"));
            brasserieWindow.updateWindowContent(new SearchDocumentWithEventForm());
        });

        searchItems[2].addActionListener(e -> {
            brasserieWindow.updateWindowContent(new SearchPaymentForm());
        });

        // CRUD
        JMenu crudMenu = new JMenu("CRUD");

        menuBar.add(crudMenu);

        crudItem[0] = new JMenuItem("Process");
        crudItem[1] = new JMenuItem("Document??");

        for (JMenuItem menuItem : crudItem)
        {
            crudMenu.add(menuItem);
        }

        crudItem[0].addActionListener(e -> {
            brasserieWindow.updateWindowContent(new ProcessPanel());
        });

    }

    public JMenuBar getMenuBar(){
        return menuBar;
    }
}
