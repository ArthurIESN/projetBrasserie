package UI.Components;

import BusinessLogic.InterfaceSubject.Subject;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SearchByLabelPanel<T> extends JPanel
{
    private JEnhancedTextField searchField;
    private JList<String> resultList;
    private DefaultListModel<String> listModel;
    private List<T> data;
    private List<T> filteredData;
    private Function<T, String> toStringFunction;

    public SearchByLabelPanel(List<T> data, String searchPlaceholder, Function<T, String> toStringFunction)
    {
        this.data = data;
        this.toStringFunction = toStringFunction;
        setLayout(new BorderLayout());

        // Create search field
        searchField = new JEnhancedTextField();
        searchField.setPlaceholder(searchPlaceholder);

        add(searchField, BorderLayout.NORTH);

        // Create list model and result list
        listModel = new DefaultListModel<>();
        resultList = new JList<>(listModel);
        JScrollPane scrollPanel = new JScrollPane(resultList);

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

    public SearchByLabelPanel(List<T> data, Function<T, String> toStringFunction)
    {
        this(data, "", toStringFunction);
    }

    public void onSelectedItemChange(Runnable runnable)
    {
        resultList.addListSelectionListener(e ->
        {
            if (!e.getValueIsAdjusting())
            {
                runnable.run();
            }
        });
    }

    private void updateList()
    {
        String searchText = searchField.getText().toLowerCase();

        listModel.clear();
        filteredData = new ArrayList<>();

        for (T item : data)
        {
            if (toStringFunction.apply(item).toLowerCase().contains(searchText))
            {
                listModel.addElement(toStringFunction.apply(item));
                filteredData.add(item);
            }
        }
    }

    public T getSelectedItem()
    {
        if (resultList.getSelectedIndex() < 0)
        {
            return null;
        }

        return filteredData.get(resultList.getSelectedIndex());
    }

    public void setSelectedItem(T item)
    {
        searchField.updateText(toStringFunction.apply(item));
        updateList();

        resultList.setSelectedIndex(filteredData.indexOf(item));
    }
}