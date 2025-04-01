package UI.Components;

import java.awt.*;

import javax.swing.*;

public class GridBagLayoutHelper extends JPanel
{
    private final GridBagConstraints gbc;

    public GridBagLayoutHelper()
    {
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        setLayout(new GridBagLayout());

    }

    public void addField(String label, Component component)
    {
        JLabel jLabel = new JLabel(label);
        gbc.gridx = 0;
        gbc.gridy++;
        add(jLabel, gbc);

        gbc.gridx = 1;
        add(component, gbc);
    }

    // Field without label. Takes the whole width
    public void addField(Component component)
    {
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        add(component, gbc);
        gbc.gridwidth = 1;
    }
}