package UI.Components;

import UI.Theme.ThemeManager;
import UI.Theme.ThemeObserver;

import java.awt.*;
import java.awt.event.HierarchyEvent;
import java.util.Properties;

import javax.swing.*;

public class GridBagLayoutHelper extends JScrollPane implements ThemeObserver
{
    private final GridBagConstraints gbc;
    private final JPanel contentPanel;
    private static Color backgroundColor;


    /**
     * Constructor for GridBagLayoutHelper.
     * Initializes the GridBagConstraints and sets the layout to GridBagLayout.
     */
    public GridBagLayoutHelper()
    {
        ThemeManager.getInstance().addObserver(this);

        contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(backgroundColor);
        setViewportView(contentPanel);

        setBorder(BorderFactory.createEmptyBorder());

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        addHierarchyListener(e -> {
            if ((e.getChangeFlags() & HierarchyEvent.DISPLAYABILITY_CHANGED) != 0) {
                if (!isDisplayable()) {
                    dispose();
                }
            }
        });
    }

    private void dispose()
    {
        ThemeManager.getInstance().removeObserver(this);
    }

    public static void onThemeChangedStatic(Properties themeProperties)
    {
        if (themeProperties != null)
        {
            backgroundColor = Color.decode(themeProperties.getProperty("GridBagLayoutHelper.backgroundColor") != null ? themeProperties.getProperty("GridBagLayoutHelper.backgroundColor") : "#FFFFFF");
        }
    }


    // Override the getComponents method to return the components of the content panel instead of the scroll pane
    @Override
    public Component[] getComponents()
    {
        return contentPanel.getComponents();
    }

    public void scrollToTop()
    {
        getVerticalScrollBar().setValue(0);
    }

    public void scrollToBottom()
    {
        SwingUtilities.invokeLater(() ->
        {
            JScrollBar vertical = getVerticalScrollBar();
            vertical.setValue(vertical.getMaximum());
        });
    }

    /**
     * Adds a component to the panel with specified label and component.
     * The component is added in a new row with the label on the left and the component on the right.
     *
     * @param label    the label for the component
     * @param component the component to be added
     */
    public void addField(String label, Component component)
    {
        JPanel fieldPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        JLabel jLabel = new JLabel(label, SwingConstants.RIGHT);

        fieldPanel.add(jLabel);
        fieldPanel.add(component);

        fieldPanel.setPreferredSize(new Dimension(jLabel.getPreferredSize().width + component.getPreferredSize().width + 200, component.getPreferredSize().height ));

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        contentPanel.add(fieldPanel, gbc);

        gbc.gridwidth = 1;
    }

    /**
     * Adds a component to the panel with specified component.
     * The component is added in a new row with no label. The component is forced to the right.
     *
     * @param component the component to be added
     */
    public void addRightField(Component component)
    {
        addField("", component);
    }

    /**
     * Adds a component to the panel without a label.
     * The component is added in a new row and will take the full width of the row.
     *
     * @param component the component to be added
     */
    public void addField(Component component)
    {
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        contentPanel.add(component, gbc);
        gbc.gridwidth = 1;
    }

    @Override
    public void onThemeChanged(Properties themeProperties)
    {
        // Set background color
        contentPanel.setBackground(backgroundColor);
    }
}