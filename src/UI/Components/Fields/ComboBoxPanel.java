package UI.Components.Fields;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.function.Function;

public class ComboBoxPanel<T> extends JPanel
{
    private final ArrayList<T> items;
    private final JComboBox<T> comboBox;

    /**
     * Constructor for ComboBoxPanel.
     * @param items The list of items to be displayed in the combo box.
     * @param toStringFunction A function that converts an item of type T to a String for display.
     */
    public ComboBoxPanel(ArrayList<T> items, Function<T, String> toStringFunction)
    {
        this.items = items;

        setLayout(new BorderLayout());

        comboBox = new JComboBox<>();
        for(T item : items)
        {
            comboBox.addItem(item);
        }

        comboBox.setRenderer(new DefaultListCellRenderer()
        {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus)
            {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value != null)
                {
                    // Here we use @SuppressWarnings to avoid unchecked cast warning
                    // because we know that the items in the comboBox are of type T
                    @SuppressWarnings("unchecked")
                    T item = (T) value;
                    setText(toStringFunction.apply(item));
                }
                return this;
            }
        });

        add(comboBox, BorderLayout.CENTER);
    }

    /**
     * This method is used to add an action listener to the combo box.
     * It will be triggered when the selected item changes.
     * @param action The action listener to be added.
     */
    public void onSelectedItemChange(ActionListener action)
    {
        comboBox.addActionListener(e ->
        {
            T selectedItem = getSelectedItem();
            if (selectedItem != null)
            {
                action.actionPerformed(e);
            }
        });
    }

    // Here we use @SuppressWarnings to avoid unchecked cast warning
    // because we know that the items in the comboBox are of type T

    /**
     * This method is used to get the selected item from the combo box.
     * It returns the selected item of type T.
     * @return The selected item of type T.
     */
    @SuppressWarnings("unchecked")
    public T getSelectedItem()
    {
        return (T) comboBox.getSelectedItem();
    }

    /**
     * This method is used to set the selected item in the combo box.
     * It takes an item of type T as a parameter and sets it as the selected item.
     * @param item The item to be set as the selected item.
     */
    public void setSelectedItem(T item)
    {
        comboBox.setSelectedItem(item);
    }

    /**
     * This method is used to set the selected item in the combo box by index.
     * It takes an index as a parameter and sets the item at that index as the selected item.
     * @param index The index of the item to be set as the selected item.
     */
    public void setSelectedItem(int index)
    {
        if (index >= 0 && index < items.size())
        {
            comboBox.setSelectedItem(items.get(index));
        }
    }

}
