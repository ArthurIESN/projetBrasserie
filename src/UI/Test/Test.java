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
    public Test()
    {

        GridBagLayoutHelper gridBagLayoutHelper = new GridBagLayoutHelper();


        // Je met des data par defaut pour tester mais on est pas obligé de le faire

        ArrayList<String> data = new ArrayList<>();
        data.add("Item 1");
        data.add("Item 2");
        data.add("Item 3");
        SearchByLabelPanel<String> searchByLabelPanel = new SearchByLabelPanel<>(data, String::toString);


        ArrayList<String> data2 = new ArrayList<>();
        data2.add("data 1");
        data2.add("data 2");
        data2.add("data 3");
        SearchByLabelPanel<String> searchByLabelPanel2 = new SearchByLabelPanel<>(data2, String::toString);

        ArrayList<String> data3 = new ArrayList<>();
        data3.add("beta 1");
        data3.add("beta 2");
        data3.add("beta 3");
        SearchByLabelPanel<String> searchByLabelPanel3 = new SearchByLabelPanel<>(data3, String::toString);

        gridBagLayoutHelper.addField(searchByLabelPanel);
        gridBagLayoutHelper.addField(searchByLabelPanel2);
        gridBagLayoutHelper.addField(searchByLabelPanel3);



        StepByStepManager stepByStepManager = new StepByStepManager(gridBagLayoutHelper.getComponents());

        searchByLabelPanel.onSelectedItemChange(
                selectedItem -> stepByStepManager.completeStep(0)
        );

        stepByStepManager.onStepShown(0, (v) ->
        {
            System.out.println("Step 0 shown");
        });

        stepByStepManager.onStepShown(1, (v) ->
        {
            updateStepTwo(searchByLabelPanel, searchByLabelPanel2);
        });

        stepByStepManager.onStepShown(2, (v) ->
        {
            System.out.println("Step 2 shown");
        });

        searchByLabelPanel2.onSelectedItemChange(
                selectedItem -> stepByStepManager.completeStep(1)
        );

        add(gridBagLayoutHelper);
    }

    // ici je passe les search par argument car j'ai la flemme. Faut pas faire ca
    private void updateStepTwo(SearchByLabelPanel search1, SearchByLabelPanel search2)
    {

        // Request with search1 SelectedItem
        // result = AppController.getData(search1.getSelectedItem());

        // Update search2 with the result of the request
        //search2.setData(result);

    }
}
