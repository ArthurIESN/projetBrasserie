package UI.Search;

import javax.swing.*;

import UI.Components.JDualSliderPanel;
import UI.Components.GridBagLayoutHelper;

import java.awt.*;

public class SearchItemForm extends JPanel
{
    public SearchItemForm()
    {
        // add a title
        setLayout(new BorderLayout());
        JLabel title = new JLabel("Search Items");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        JPanel searchForm = new JPanel(new GridBagLayout());
        GridBagLayoutHelper gridSearchForm = new GridBagLayoutHelper(searchForm);

        // TVA Code field
        JTextField tvaCodeField = new JTextField();
        gridSearchForm.addComponent("TVA Code", tvaCodeField);

        // NbItemInStock field
        //@todo (get the max from the database ?)
        JDualSliderPanel nbItemInPackaging = new JDualSliderPanel(1, 100 , 1, 100, 300, 50);
        gridSearchForm.addComponent("Number of Items in Stock", nbItemInPackaging);

        // Range price field
        JDualSliderPanel priceRange = new JDualSliderPanel(0, 150, 0, 150, 300, 50);
        gridSearchForm.addComponent("Price Range", priceRange);

        add(searchForm, BorderLayout.CENTER);
    }
}
