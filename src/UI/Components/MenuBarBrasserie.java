package UI.Components;

import Environement.SystemProperties;
import UI.BusinessTasks.CustomerOrderPanel;
import UI.BusinessTasks.RestockItemPanel;
import UI.Document.DocumentPanel;
import UI.Employee.EmployeePanel;
import UI.Windows.BrasserieWindow.BrasserieCredits;
import UI.Windows.BrasserieWindow.BrasserieWindow;
import UI.Windows.BrasserieWindow.BrasserieHomePanel;
import UI.Process.ProcessPanel;
import UI.Search.Document.SearchDocumentWithEventForm;
import UI.Search.Item.SearchItemForm;
import UI.Search.Payment.SearchPaymentPanel;
import UI.Windows.WindowManager;
import Controller.AppController;

import javax.swing.*;

public class MenuBarBrasserie {
    private final JMenuBar menuBar;

    public MenuBarBrasserie(BrasserieWindow brasserieWindow){
        menuBar = new JMenuBar();

        JMenu brasserieMenu = new JMenu("Brasserie");

        menuBar.add(brasserieMenu);

        JMenuItem homeItem = new JMenuItem("Home");
        JMenuItem addWindowItem = new JMenuItem("Add Window");
        JMenuItem creditsItem = new JMenuItem("Credits");
        JMenuItem disconnectItem = new JMenuItem("Disconnect");
        JMenuItem quitItem = new JMenuItem("Quit");

        brasserieMenu.add(homeItem);

        if(SystemProperties.getSystemType() != SystemProperties.SystemType.MAC)
        {
            brasserieMenu.addSeparator();

            JMenuItem settingsItem = new JMenuItem("Settings");
            settingsItem.addActionListener(e ->
                    WindowManager.showSettingsWindow());

            brasserieMenu.add(settingsItem);
        }

        disconnectItem.addActionListener(e ->
                AppController.disconnect());

        brasserieMenu.add(addWindowItem);
        brasserieMenu.add(creditsItem);
        brasserieMenu.add(disconnectItem);
        brasserieMenu.add(quitItem);

        homeItem.addActionListener(e ->
                brasserieWindow.updateWindowContent(new BrasserieHomePanel()));

        homeItem.doClick();

        addWindowItem.addActionListener(e ->
                WindowManager.addWindow());

        creditsItem.addActionListener(e ->
                brasserieWindow.updateWindowContent(new BrasserieCredits()));

        quitItem.addActionListener(e ->
        {
            WindowManager.saveWindows();
            System.exit(0);
        });


        JMenu searchMenu = new JMenu("Searches");

        menuBar.add(searchMenu);

        JMenuItem[] searchItems = new JMenuItem[3];
        searchItems[0] = new JMenuItem("1 : Search Item");
        searchItems[1] = new JMenuItem("2 : Search Document");
        searchItems[2] = new JMenuItem("3 : Search Payment");

        for (JMenuItem menuItem : searchItems)
        {
            searchMenu.add(menuItem);
        }

        searchItems[0].addActionListener(e ->
                brasserieWindow.updateWindowContent(new SearchItemForm()));

        searchItems[1].addActionListener(e -> {
            JPanel panel2 = new JPanel();
            panel2.add(new JLabel("Recherche2 selected"));
            brasserieWindow.updateWindowContent(new SearchDocumentWithEventForm());
        });

        searchItems[2].addActionListener(e -> brasserieWindow.updateWindowContent(new SearchPaymentPanel()));

        // CRUD
        JMenu crudMenu = new JMenu("CRUD");

        menuBar.add(crudMenu);

        JMenuItem[] crudItem = new JMenuItem[3];
        crudItem[0] = new JMenuItem("Process");
        crudItem[1] = new JMenuItem("Document");
        crudItem[2] = new JMenuItem("Employee");

        for (JMenuItem menuItem : crudItem)
        {
            crudMenu.add(menuItem);
        }

        crudItem[0].addActionListener(e -> brasserieWindow.updateWindowContent(new ProcessPanel()));

        crudItem[1].addActionListener(e -> brasserieWindow.updateWindowContent(new DocumentPanel()));

        crudItem[2].addActionListener(e -> brasserieWindow.updateWindowContent(new EmployeePanel()));

        // Job Task
        JMenu jobTaskMenu = new JMenu("Job Task");
        menuBar.add(jobTaskMenu);

        JMenuItem[] jobTaskItem = new JMenuItem[2];
        jobTaskItem[0] = new JMenuItem("Restock Item");
        jobTaskItem[1] = new JMenuItem("Customer Order");

        for (JMenuItem menuItem : jobTaskItem)
        {
            jobTaskMenu.add(menuItem);
        }

        jobTaskItem[0].addActionListener(e ->
                brasserieWindow.updateWindowContent(new RestockItemPanel()));

        jobTaskItem[1].addActionListener(e -> brasserieWindow.updateWindowContent(new CustomerOrderPanel()));

    }

    public JMenuBar getMenuBar(){
        return menuBar;
    }
}
