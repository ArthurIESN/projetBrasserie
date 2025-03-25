package UI.Components;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class YearComboBox extends JPanel {
    private JComboBox<Integer> yearsComboBox;
    private List<Integer> years = new ArrayList<>();
    private Integer selectedYear;

    public YearComboBox(List<Integer> years){
        this.years = years;

        DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<>();

        for (Integer year : years){
            model.addElement(year);
        }

        yearsComboBox = new JComboBox<>(model);
        add(yearsComboBox);

        yearsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = yearsComboBox.getSelectedIndex();
                if(selectedIndex != -1){
                    selectedYear = (int) yearsComboBox.getSelectedItem();
                }

                System.out.println(selectedYear);

            }
        });

    }

}
