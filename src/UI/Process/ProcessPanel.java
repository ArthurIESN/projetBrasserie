package UI.Process;

import UI.Components.Navbar.NavbarPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import Model.Process.Process;

public class ProcessPanel extends JPanel
{
    private Container container;
    private final NavbarPanel navbarPanel;
    private final List<ProcessObserver> observers = new ArrayList<>();
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
        navbarPanel = new NavbarPanel(items, this::updateContent);

        add(navbarPanel, BorderLayout.NORTH);
        add(container, BorderLayout.CENTER);
    }

    public void navbarClick(int index)
    {
        navbarPanel.clickItem(index);
    }

    public void addObserver(ProcessObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ProcessObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Object data)
    {
        for (ProcessObserver observer : observers)
        {
            observer.update(data);
        }
    }


    public void updateContent(int index)
    {
        container.removeAll();

        JPanel panel = switch (index)
        {
            case 0 -> new CreateProcessPanel();
            case 1 -> new ReadProcessPanel(this);
            case 2 -> new UpdateProcessPanel(this);
            case 3 -> new DeleteProcessPanel(this);
            default -> new CreateProcessPanel();
        };

        container.add(panel, BorderLayout.CENTER);
        container.revalidate();
        container.repaint();
    }
}

