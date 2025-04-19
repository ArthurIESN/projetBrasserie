package UI.Components;

import Environement.SystemProperties;
import UI.Document.DocumentPanel;
import UI.Windows.BrasserieWindow.BrasserieCredits;
import UI.Windows.BrasserieWindow.BrasserieWindow;
import UI.Windows.BrasserieWindow.BrasserieHomePanel;
import UI.Process.ProcessPanel;
import UI.Search.Document.SearchDocumentWithEventForm;
import UI.Search.Item.SearchItemForm;
import UI.Search.Payment.SearchPaymentForm;
import UI.Windows.WindowManager;

import javax.swing.*;

public class MenuBarBrasserie {
    private JMenuBar menuBar;
    private JMenu brasserieMenu;
    private JMenu searchMenu;
    private final JMenuItem[] searchItems = new JMenuItem[3];
    private final JMenuItem[] crudItem = new JMenuItem[2];
    private final JMenuItem[] jobTaskItem = new JMenuItem[2];

    public MenuBarBrasserie(BrasserieWindow brasserieWindow){
        menuBar = new JMenuBar();

        brasserieMenu = new JMenu("Brasserie");

        menuBar.add(brasserieMenu);

        JMenuItem homeItem = new JMenuItem("Home");
        JMenuItem addWindowItem = new JMenuItem("Add Window");
        JMenuItem creditsItem = new JMenuItem("Credits");
        JMenuItem quitItem = new JMenuItem("Quit");

        brasserieMenu.add(homeItem);

        if(SystemProperties.getSystemType() != SystemProperties.SystemType.MAC)
        {
            brasserieMenu.addSeparator();

            JMenuItem settingsItem = new JMenuItem("Settings");
            settingsItem.addActionListener(e ->
            {
                WindowManager.showSettingsWindow();
            });

            brasserieMenu.add(settingsItem);
        }

        brasserieMenu.add(addWindowItem);
        brasserieMenu.add(creditsItem);
        brasserieMenu.add(quitItem);

        homeItem.addActionListener(e ->
        {
            brasserieWindow.updateWindowContent(new BrasserieHomePanel());
        });

        homeItem.doClick();

        addWindowItem.addActionListener(e ->
        {
            WindowManager.addWindow();
        });

        creditsItem.addActionListener(e ->
        {
            brasserieWindow.updateWindowContent(new BrasserieCredits());
        });

        quitItem.addActionListener(e ->
        {
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
        crudItem[1] = new JMenuItem("Document");

        for (JMenuItem menuItem : crudItem)
        {
            crudMenu.add(menuItem);
        }

        crudItem[0].addActionListener(e -> {
            brasserieWindow.updateWindowContent(new ProcessPanel());
        });

        crudItem[1].addActionListener(e -> {
            brasserieWindow.updateWindowContent(new DocumentPanel());
        });

        // Job Task
        JMenu jobTaskMenu = new JMenu("Job Task");
        menuBar.add(jobTaskMenu);

        jobTaskItem[0] = new JMenuItem("Job Task 1");
        jobTaskItem[1] = new JMenuItem("Job Task 2");

        for (JMenuItem menuItem : jobTaskItem)
        {
            jobTaskMenu.add(menuItem);
        }

        jobTaskItem[0].addActionListener(e -> {
            JPanel panel1 = new JPanel();
            panel1.add(new JLabel("Job Task 1 selected"));
            brasserieWindow.updateWindowContent(panel1);
        });

        jobTaskItem[1].addActionListener(e -> {
            JPanel panel2 = new JPanel();
            panel2.add(new JLabel("Job Task 2 selected"));
            brasserieWindow.updateWindowContent(panel2);
        });

    }

    public JMenuBar getMenuBar(){
        return menuBar;
    }
}
