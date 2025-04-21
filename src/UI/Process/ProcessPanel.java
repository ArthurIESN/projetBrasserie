package UI.Process;

import UI.Components.Navbar.NavbarPanel;

import Model.Process.Process;
import UI.Windows.WindowManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProcessPanel extends JPanel implements ProcessSubject
{
    private Container container;
    private final NavbarPanel navbarPanel;
    private static final List<ProcessObserver> observers = new ArrayList<>();
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



    public void navbarForceClick(int index)
    {
        navbarPanel.forceClickItem(index);
    }

    @Override
    public void addObserver(ProcessObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ProcessObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Process process)
    {
        for (ProcessObserver observer : observers)
        {
            observer.update(process);
        }
    }

    public void moveTo(int index)
    {
        Class<? extends JPanel> panelClass = getClassWithIndex(index);

        if (WindowManager.isPanelDisplayed(panelClass))
        {
            WindowManager.focusWindow(panelClass);
        }
        else
        {
            navbarPanel.clickItem(index);
        }
    }

    public void updateContent(int index)
    {
        container.removeAll();

        Class<? extends JPanel> panelClass = getClassWithIndex(index);

        JPanel panel;
        try
        {

            panel = panelClass
                    .getDeclaredConstructor(ProcessPanel.class)
                    .newInstance(this);
        }
        catch (Exception e)
        {
            panel = new CreateProcessPanel(this);
        }

        container.add(panel, BorderLayout.CENTER);
        container.revalidate();
        container.repaint();
    }

    private Class<? extends JPanel> getClassWithIndex(int index)
    {
        return switch (index)
        {
            case 0 -> CreateProcessPanel.class;
            case 1 -> ReadProcessPanel.class;
            case 2 -> UpdateProcessPanel.class;
            case 3 -> DeleteProcessPanel.class;
            default -> CreateProcessPanel.class;
        };
    }
}

