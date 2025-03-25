package UI.Components;

import Model.Item.Item;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class ItemComboBox extends JPanel{
    private JComboBox<String> comboBox;
    private List<Item> items;
    private int selectedItemId;

    public ItemComboBox(List<Item> items){
        this.items = items;
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        for(Item item : items){
            model.addElement(item.getLabel());
        }

        comboBox = new JComboBox<>(model);
        add(comboBox);

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = comboBox.getSelectedIndex();
                if(selectedIndex != -1){
                    Item selectedItem = items.get(selectedIndex);
                    selectedItemId = selectedItem.getId();
                }
            }
        });
    }

    public int getSelectedItemId(){
        return selectedItemId;
    }

}