package UI.Employee;

import Model.Employee.Employee;
import Model.Process.Process;
import UI.Components.Navbar.NavbarPanel;
import UI.Process.*;
import UI.Windows.WindowManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeePanel extends JPanel implements EmployeeSubject
{
    private Container container;
    private final NavbarPanel navbarPanel;
    private static final List<EmployeeObserver> observers = new ArrayList<>();

    public EmployeePanel()
    {
        setLayout(new BorderLayout());

        container = new JPanel();
        container.setLayout(new BorderLayout());

        ArrayList<String> items = new ArrayList<>();
        items.add("Create Employee");
        items.add("Read Employee");
        items.add("Update Employee");
        items.add("Delete Employee");
        navbarPanel = new NavbarPanel(items, this::updateContent);

        add(navbarPanel, BorderLayout.NORTH);
        add(container, BorderLayout.CENTER);
    }

    public void navbarForceClick(int index)
    {
        navbarPanel.forceClickItem(index);
    }

    @Override
    public void addObserver(EmployeeObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(EmployeeObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Employee employee)
    {
        for (EmployeeObserver observer : observers)
        {
            observer.update(employee);
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
                    .getDeclaredConstructor(EmployeePanel.class)
                    .newInstance(this);
        }
        catch (Exception e)
        {
            panel = new CreateEmployeePanel(this);
        }

        container.add(panel, BorderLayout.CENTER);
        container.revalidate();
        container.repaint();
    }

    private Class<? extends JPanel> getClassWithIndex(int index)
    {
        return switch (index)
        {
            case 0 -> CreateEmployeePanel.class;
            case 1 -> ReadEmployeePanel.class;
            case 2 -> UpdateEmployeePanel.class;
            case 3 -> DeleteEmployeePanel.class;
            default -> CreateEmployeePanel.class;
        };
    }
}
