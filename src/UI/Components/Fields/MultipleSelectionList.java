package UI.Components.Fields;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class MultipleSelectionList<T> extends JPanel {
    private final JList<String> list;
    private final DefaultListModel<String> listModel;
    private final ArrayList<T> originalData;
    private final ArrayList<T> filteredData;
    private final HashSet<T> selectedItems;
    private final Function<T, String> toStringFunction;
    private Consumer<ArrayList<T>> onSelectionChange;
    private int currentIndex = -1;
    private final JEnhancedTextField searchField;

    public MultipleSelectionList(ArrayList<T> items, Function<T, String> toStringFunction) {
        this.originalData = new ArrayList<>(items);
        this.filteredData = new ArrayList<>(items);
        this.selectedItems = new HashSet<>();
        this.toStringFunction = toStringFunction;

        setLayout(new BorderLayout());

        searchField = new JEnhancedTextField();
        searchField.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filterList(); }
            public void removeUpdate(DocumentEvent e) { filterList(); }
            public void changedUpdate(DocumentEvent e) { filterList(); }
        });
        add(searchField, BorderLayout.NORTH);

        // List model and list
        listModel = new DefaultListModel<>();
        for (T item : filteredData) {
            listModel.addElement(toStringFunction.apply(item));
        }

        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setCellRenderer(new CustomListCellRenderer<>());

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                currentIndex = list.locationToIndex(e.getPoint());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                currentIndex = -1;
            }
        });

        list.setSelectionModel(new DefaultListSelectionModel() {
            @Override
            public void setSelectionInterval(int index0, int index1) {
                if (currentIndex == -1) currentIndex = index0;
                if (index0 != currentIndex || index0 != index1) return;

                if (index0 < 0 || index0 >= filteredData.size()) return;

                T item = filteredData.get(index0);
                if (selectedItems.contains(item)) {
                    selectedItems.remove(item);
                } else {
                    selectedItems.add(item);
                }

                updateSelectionVisuals();

                if (onSelectionChange != null) {
                    onSelectionChange.accept(getSelectedItems());
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        add(scrollPane, BorderLayout.CENTER);
    }

    private void filterList() {
        String query = searchField.getText().toLowerCase();
        filteredData.clear();
        listModel.clear();

        for (T item : originalData) {
            String displayText = toStringFunction.apply(item);
            if (displayText.toLowerCase().contains(query)) {
                filteredData.add(item);
                listModel.addElement(displayText);
            }
        }

        updateSelectionVisuals();
    }

    private void updateSelectionVisuals() {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < filteredData.size(); i++) {
            if (selectedItems.contains(filteredData.get(i))) {
                indices.add(i);
            }
        }
        list.setSelectedIndices(indices.stream().mapToInt(i -> i).toArray());
    }

    public void clearSelection() {
        selectedItems.clear();
        updateSelectionVisuals();
        if (onSelectionChange != null) {
            onSelectionChange.accept(getSelectedItems());
        }
    }

    public void setOnSelectionChange(Consumer<ArrayList<T>> onSelectionChange) {
        this.onSelectionChange = onSelectionChange;
    }

    public ArrayList<T> getSelectedItems() {
        return new ArrayList<>(selectedItems);
    }

    public JEnhancedTextField getSearchField() {
        return searchField;
    }

    private static class CustomListCellRenderer<T> extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (isSelected) {
                component.setBackground(new Color(80, 80, 80));
                component.setForeground(Color.WHITE);
            } else {
                component.setForeground(Color.WHITE);
            }
            return component;
        }
    }
}
