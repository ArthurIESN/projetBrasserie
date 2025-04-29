package UI.Components.Fields;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class MultipleSelectionList<T> extends JPanel {
    private JList<String> list;
    private ArrayList<T> data;
    private Function<T, String> toStringFunction;
    private Consumer<ArrayList<T>> onSelectionChange;


    public MultipleSelectionList(ArrayList<T> items, Function<T,String> toStringFunction) {
        this.data = items;
        this.toStringFunction = toStringFunction;

        setLayout(new BorderLayout());

        ArrayList<String> displayItems = new ArrayList<>();

        for (T item : data){
            displayItems.add(toStringFunction.apply(item));
        }

        list =  new JList<>(displayItems.toArray(new String[0]));
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setCellRenderer(new CustomListCellRenderer<>());

        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting() && onSelectionChange != null){
                    onSelectionChange.accept(getSelectedItems());
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(list);

        add(scrollPane);

    }

    public void setOnSelectionChange(Consumer<ArrayList<T>> onSelectionChange) {
        this.onSelectionChange = onSelectionChange;
    }

    public ArrayList<T> getSelectedItems() {
        ArrayList<T> selectedItems = new ArrayList<>();
        for(int index : list.getSelectedIndices()) {
            selectedItems.add(data.get(index));
        }
        return selectedItems;
    }


    private static class CustomListCellRenderer<T> extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (isSelected) {
                component.setBackground(Color.RED);
                component.setForeground(Color.WHITE);
            } else {
                component.setForeground(Color.WHITE);
            }

            return component;
        }
    }
}
