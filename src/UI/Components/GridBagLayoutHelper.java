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
        JPanel fieldPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        fieldPanel.add(new JLabel(label, SwingConstants.RIGHT));
        fieldPanel.add(component);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        add(fieldPanel, gbc);

        gbc.gridwidth = 1;
    }

    public void addRightField(Component component)
    {
        addField("", component);
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