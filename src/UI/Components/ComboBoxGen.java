package UI.Components;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ComboBoxGen<T> extends JPanel {
    private JComboBox<T> genComboBox;
    private T tSelected;

    public ComboBoxGen(List<T> tList){
        List<T> tArrayList = tList;

        DefaultComboBoxModel<T> model = new DefaultComboBoxModel<>();

        for (T year : tList){
            model.addElement(year);
        }

        genComboBox = new JComboBox<>(model);
        add(genComboBox);

        genComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = genComboBox.getSelectedIndex();
                if(selectedIndex != -1){
                    tSelected = (T) genComboBox.getSelectedItem();
                }

                System.out.println(tSelected);

            }
        });

    }

}
