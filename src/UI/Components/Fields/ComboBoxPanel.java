package UI.Components.Fields;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Function;

public class ComboBoxPanel<T> extends JComboBox<String>
{
    private Function<T, String> toStringFunction;
    private ArrayList<T> items;
    public ComboBoxPanel(ArrayList<T> items, Function<T, String> toStringFunction)
    {
        this.items = items;
        this.toStringFunction = toStringFunction;

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (T item : items)
        {
            model.addElement(toStringFunction.apply(item));
        }

        setModel(model);
    }

    public void onSelectedItemChange(ActionListener action)
    {
        addActionListener(e -> {
            T selectedItem = getSelectedItem();
            if (selectedItem != null)
            {
                action.actionPerformed(e);
            }
        });
    }

    // Here we can suppress the warning because we know that the selected item is of type T
    @SuppressWarnings("unchecked")
    public T getSelectedItem()
    {
        return (T) super.getSelectedItem();
    }
}
