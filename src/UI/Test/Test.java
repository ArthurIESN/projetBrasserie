package UI.Test;

import BusinessLogic.Utils.HashUtils;
import Utils.Utils;

import Controller.Customer.CustomerController;

import Exceptions.Customer.GetAllCustomersException;

import Model.Customer.Customer;

import UI.Components.Fields.ComboBoxPanel;
import UI.Components.Fields.JNumberField;
import UI.Components.Fields.SearchListPanel;
import UI.Components.GridBagLayoutHelper;
import UI.Components.StepManager;
import UI.Threads.LoadingThread;
import UI.Windows.WindowManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Test extends JPanel
{

    private SearchListPanel<String> searchListPanel;
    private SearchListPanel<String> searchListPanel2;
    private SearchListPanel<String> searchListPanel3;
    private StepManager stepManager;

    public Test()
    {
        setLayout(new BorderLayout());
        JLabel title = new JLabel("TEST");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);
        GridBagLayoutHelper gridBagLayoutHelper = new GridBagLayoutHelper();

        // YA DES BUGS POUR L'INSTANT

        ArrayList<String> data = new ArrayList<>();
        data.add("Item 1");
        data.add("Item 2");
        data.add("Item 3");
        searchListPanel = new SearchListPanel<>(data, String::toString);


        ArrayList<String> data2 = new ArrayList<>();
        data2.add("data 1");
        data2.add("data 2");
        data2.add("data 3");
        searchListPanel2 = new SearchListPanel<>(data2, String::toString);

        ArrayList<String> data3 = new ArrayList<>();
        data3.add("beta 1");
        data3.add("beta 2");
        data3.add("beta 3");
        searchListPanel3 = new SearchListPanel<>(data3, String::toString);

        try{
            ArrayList<Customer> customers = CustomerController.getAllCustomers();

            ComboBoxPanel<Customer> comboBoxPanel = new ComboBoxPanel<>(customers, Customer::getFirstName
            );

            comboBoxPanel.onSelectedItemChange(
                    selectedItem -> {
                        System.out.println("Selected item: " + selectedItem);
                    }
            );
            gridBagLayoutHelper.addField(comboBoxPanel);
        } catch (GetAllCustomersException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        stepManager = new StepManager(gridBagLayoutHelper.getComponents());

        searchListPanel.onSelectedItemChange(
                selectedItem -> {
                    System.out.println("Search 1 clicked");
                    stepManager.completeStep(0);

                }
        );

        searchListPanel2.onSelectedItemChange(
                selectedItem -> {
                    System.out.println("Search 2 clicked");
                    stepManager.completeStep(1);

                }
        );

        searchListPanel3.onSelectedItemChange(
                selectedItem ->
                {
                    System.out.println("Search 3 clicked");
                    stepManager.completeStep(2);

                }
        );

        stepManager.onStepShown(0, this::functionCalledWhenStepOneIsShown);

        stepManager.onStepShown(1, this::functionCalledWhenStepTwoIsShown);

        stepManager.onStepShown(2, this::functionCalledWhenStepThreeIsShown);


        JNumberField test = new JNumberField(Utils.NumberType.INTEGER);
        test.setAllowNegative(true);
        test.setPlaceholder("INTEGER");

        JNumberField test2 = new JNumberField(Utils.NumberType.FLOAT, 3);
        test2.setAllowNegative(true);
        test2.setPlaceholder("FLOAT");

        JButton test3 = new JButton("THREAD");

        test3.addActionListener(e -> {
            System.out.println("Thread started");
            LoadingThread loadingThread = new LoadingThread("toN GROs Daron", "Calcule du salaire de Didier", 5000);

            WindowManager.setWindowsEnable(false);
            loadingThread.start();

            loadingThread.onLoadingComplete(() ->
            {
                System.out.println("Thread completed");
                WindowManager.setWindowsEnable(true);

                JOptionPane.showMessageDialog(null, "99999 Euros", "Info", JOptionPane.INFORMATION_MESSAGE);
            });
        });

        gridBagLayoutHelper.addField("sa", test);
        gridBagLayoutHelper.addField(test2);
        gridBagLayoutHelper.addField(test3);

        add(gridBagLayoutHelper);
    }

    private void functionCalledWhenStepOneIsShown()
    {
        System.out.println("Step 1 shown");
        searchListPanel.setSelectedItem(null);
    }

    private void functionCalledWhenStepTwoIsShown()
    {
        System.out.println("Step 2 shown");
        searchListPanel2.setSelectedItem(null);

        int randomBoolean = (int) (Math.random() * 2);
        if (randomBoolean == 0)
        {
            System.out.println("NOT STOPPED");
        }
        else
        {
            System.out.println("STOPPED");
            stepManager.stop();
        }

    }


    private void functionCalledWhenStepThreeIsShown()
    {
        System.out.println("Step 3 shown");
        searchListPanel3.setSelectedItem(null);
    }
}
