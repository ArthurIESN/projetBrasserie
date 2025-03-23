package UI.Components;

import java.awt.*;

import javax.swing.*;

public class GridBagLayoutHelper
{
    private final GridBagConstraints gbc;
    private final JPanel panel;

    public GridBagLayoutHelper(JPanel panel)
    {
        this.panel = panel;
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
    }

    public void addField(String label, Component component)
    {
        JLabel jLabel = new JLabel(label);
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(jLabel, gbc);

        gbc.gridx = 1;
        panel.add(component, gbc);
    }
}