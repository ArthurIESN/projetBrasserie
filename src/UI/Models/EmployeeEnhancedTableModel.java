package UI.Models;

import UI.Components.EnhancedTable.AbstractEnhancedTableModel;

import Model.Employee.Employee;
import Controller.Date.DateController;

import java.util.ArrayList;

public class EmployeeEnhancedTableModel extends AbstractEnhancedTableModel<Employee>
{

    public EmployeeEnhancedTableModel(ArrayList<Employee> employees)
    {
        super("Employee", new String[]{"ID", "Last Name", "First Name", "Birthdate", "Is Married", "Password"}, employees);
    }

    public EmployeeEnhancedTableModel()
    {
        this(new ArrayList<>());
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Employee employee = getData().get(rowIndex);

        if(employee == null)
        {
            return " - ";
        }

        return switch (columnIndex)
        {
            case 0 -> employee.getId();
            case 1 -> employee.getLastName();
            case 2 -> employee.getFirstName();
            case 3 -> DateController.getShowDateString(employee.getBirthDate());
            case 4 -> employee.isMarried() ? "Yes" : "No";
            case 5 -> getHidePassword(employee.getPassword());
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnIndex == 0 ? Integer.class : String.class;
    }

    private String getHidePassword(String password)
    {
        if (password == null || password.isEmpty())
        {
            return " - ";
        }
        return "*".repeat(password.length());
    }
}
