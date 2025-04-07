package UI.Test;

import UI.Components.Fields.SearchByLabelPanel;
import UI.Components.GridBagLayoutHelper;
import UI.Components.StepByStepManager;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Test extends JPanel
{

    private SearchByLabelPanel<String> searchByLabelPanel;
    private SearchByLabelPanel<String> searchByLabelPanel2;
    private SearchByLabelPanel<String> searchByLabelPanel3;

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

        gridBagLayoutHelper.addField(searchByLabelPanel);
        gridBagLayoutHelper.addField("Search 2", searchByLabelPanel2);
        gridBagLayoutHelper.addField(searchByLabelPanel3);



        StepByStepManager stepByStepManager = new StepByStepManager(gridBagLayoutHelper.getComponents());

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
                selectedItem -> {
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
    }


    private void functionCalledWhenStepThreeIsShown()
    {
        System.out.println("Step 3 shown");
        searchByLabelPanel3.setSelectedItem(null);
    }
}
