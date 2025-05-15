package UI.Components.Fields;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SearchListPanel<T> extends JPanel {
    private final JEnhancedTextField searchField;
    private final JList<String> resultList;
    private final DefaultListModel<String> listModel;
    private List<T> data;
    private List<T> filteredData;
    private final Function<T, String> toStringFunction;

    public SearchListPanel(List<T> data, Function<T, String> toStringFunction) {
        this.data = data;
        this.toStringFunction = toStringFunction;
        setLayout(new BorderLayout());

        // Create search field
        searchField = new JEnhancedTextField();

        add(searchField, BorderLayout.NORTH);

        // Create list model and result list
        listModel = new DefaultListModel<>();
        resultList = new JList<>(listModel);
        resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        resultList.setCellRenderer(new CustomListCellRenderer());
        JScrollPane scrollPanel = new JScrollPane(resultList);
        scrollPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));

        resultList.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                resultList.setSelectionBackground(new Color(80, 80, 80));

                scrollPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
            }

            @Override
            public void focusLost(FocusEvent e) {
                scrollPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
            }


        });


        scrollPanel.setPreferredSize(new Dimension(300, 60));

        add(scrollPanel, BorderLayout.CENTER);

        // Add document listener to search field
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateList();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateList();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateList();
            }
        });

        updateList();
    }

    public void onSelectedItemChange(ActionListener actionListener) {
        // set background color of selected item
        resultList.setSelectionBackground(new Color(80, 80, 80));

        resultList.addListSelectionListener(e ->
        {
            if (!e.getValueIsAdjusting() && resultList.getSelectedIndex() >= 0) {
                actionListener.actionPerformed(null);
            }
        });
    }

    private void updateList() {
        String searchText = searchField.getText().toLowerCase();

        listModel.clear();
        filteredData = new ArrayList<>();

        if(data != null)
        {
            for (T item : data) {
                if (toStringFunction.apply(item).toLowerCase().contains(searchText)) {
                    listModel.addElement(toStringFunction.apply(item));
                    filteredData.add(item);
                }
            }
        }
    }

    private static class CustomListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (isSelected) {
                component.setBackground(new Color(80, 80, 80));
            } else {
                component.setBackground(new Color(0, 0, 0, 0));
            }
            return component;
        }
    }

    public JEnhancedTextField getSearchField() {
        return searchField;
    }

    public T getSelectedItem() {
        if (resultList.getSelectedIndex() < 0) {
            return null;
        }

        return filteredData.get(resultList.getSelectedIndex());
    }

    public void setData(List<T> data) {
        this.data = data;
        setSelectedItem(null);
    }

    public void setSelectedItem(T item)
    {
        if (item == null) {
            searchField.updateText("");
            updateList();
        } else if (filteredData.contains(item))
        {
            searchField.updateText(toStringFunction.apply(item));
            updateList();
            resultList.setSelectedIndex(filteredData.indexOf(item));
        }
    }

    public void forceSetSelectedItem(T item)
    {
        if (item == null) {
            searchField.updateText("");
            updateList();
        } else if (data.contains(item))
        {
            searchField.updateText(toStringFunction.apply(item));
            updateList();
            resultList.setSelectedIndex(filteredData.indexOf(item));
        }
        else
        {
            searchField.updateText("");
            updateList();
            resultList.clearSelection();
            System.out.println("ITEM NOT FOUND");
        }
    }
}