package UI.Test;

import Controller.AppController;
import Exceptions.Customer.GetAllCustomersException;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Model.Customer.Customer;
import UI.Components.Fields.ComboBoxPanel;
import UI.Components.Fields.SearchByLabelPanel;
import UI.Components.GridBagLayoutHelper;
import UI.Components.StepByStepManager;

import javax.swing.*;
import java.util.ArrayList;

public class Test extends JPanel
{

    private SearchByLabelPanel<String> searchByLabelPanel;
    private SearchByLabelPanel<String> searchByLabelPanel2;
    private SearchByLabelPanel<String> searchByLabelPanel3;
    private StepByStepManager stepByStepManager;

    public Test()
    {

        GridBagLayoutHelper gridBagLayoutHelper = new GridBagLayoutHelper();

        // YA DES BUGS POUR L'INSTANT

        ArrayList<String> data = new ArrayList<>();
        data.add("Item 1");
        data.add("Item 2");
        data.add("Item 3");
        searchByLabelPanel = new SearchByLabelPanel<>(data, String::toString);


        ArrayList<String> data2 = new ArrayList<>();
        data2.add("data 1");
        data2.add("data 2");
        data2.add("data 3");
        searchByLabelPanel2 = new SearchByLabelPanel<>(data2, String::toString);

        ArrayList<String> data3 = new ArrayList<>();
        data3.add("beta 1");
        data3.add("beta 2");
        data3.add("beta 3");
        searchByLabelPanel3 = new SearchByLabelPanel<>(data3, String::toString);

        try{
            ArrayList<Customer> customers = AppController.getAllCustomers();

            ComboBoxPanel<Customer> comboBoxPanel = new ComboBoxPanel<>(customers, customer ->
                    customer.getFirstName() + " " + customer.getLastName()
            );

            comboBoxPanel.onSelectedItemChange(
                    selectedItem -> {
                        System.out.println("Selected item: " + selectedItem);
                    }
            );
            gridBagLayoutHelper.addField(comboBoxPanel);
        } catch (DatabaseConnectionFailedException | GetAllCustomersException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }





        stepByStepManager = new StepByStepManager(gridBagLayoutHelper.getComponents());

        searchByLabelPanel.onSelectedItemChange(
                selectedItem -> {
                    System.out.println("Search 1 clicked");
                    stepByStepManager.completeStep(0);

                }
        );

        searchByLabelPanel2.onSelectedItemChange(
                selectedItem -> {
                    System.out.println("Search 2 clicked");
                    stepByStepManager.completeStep(1);

                }
        );

        searchByLabelPanel3.onSelectedItemChange(
                selectedItem ->
                {
                    System.out.println("Search 3 clicked");
                    stepByStepManager.completeStep(2);

                }
        );

        stepByStepManager.onStepShown(0, this::functionCalledWhenStepOneIsShown);

        stepByStepManager.onStepShown(1, this::functionCalledWhenStepTwoIsShown);

        stepByStepManager.onStepShown(2, this::functionCalledWhenStepThreeIsShown);



        add(gridBagLayoutHelper);
    }

    private void functionCalledWhenStepOneIsShown()
    {
        System.out.println("Step 1 shown");
        searchByLabelPanel.setSelectedItem(null);
    }

    private void functionCalledWhenStepTwoIsShown()
    {
        System.out.println("Step 2 shown");
        searchByLabelPanel2.setSelectedItem(null);

        int randomBoolean = (int) (Math.random() * 2);
        if (randomBoolean == 0)
        {
            System.out.println("NOT STOPPED");
        }
        else
        {
            System.out.println("STOPPED");
            stepByStepManager.stop();
        }

    }


    private void functionCalledWhenStepThreeIsShown()
    {
        System.out.println("Step 3 shown");
        searchByLabelPanel3.setSelectedItem(null);
    }
}
