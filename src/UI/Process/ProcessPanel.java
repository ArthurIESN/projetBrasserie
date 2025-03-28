package UI.Process;

import UI.Components.NavbarPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class ProcessPanel extends JPanel
{
    private Container container;
    public ProcessPanel()
    {
        setLayout(new BorderLayout());

        container = new JPanel();
        container.setLayout(new BorderLayout());

        ArrayList<String> items = new ArrayList<>();
        items.add("Create Process");
        items.add("Read Process");
        items.add("Update Process");
        items.add("Delete Process");
        NavbarPanel navbarPanel = new NavbarPanel(items, this::updateContent);

        add(navbarPanel, BorderLayout.NORTH);
        add(container, BorderLayout.CENTER);
    }

    private void updateContent(int index)
    {
        container.removeAll();

        JPanel panel = switch (index) {
            case 0 -> new CreateProcessPanel();
            case 1 -> new ReadProcessPanel();
            case 2 -> new JPanel();
            case 3 -> new JPanel();
            default -> new JPanel();
        };

        container.add(panel, BorderLayout.CENTER);
        container.revalidate();
        container.repaint();
    }
}

