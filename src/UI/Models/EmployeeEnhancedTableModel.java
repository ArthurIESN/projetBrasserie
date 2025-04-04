package UI.Models;

import UI.Components.AbstractEnhancedTableModel;

import Model.Employee.Employee;

import java.util.ArrayList;

public class EmployeeEnhancedTableModel extends AbstractEnhancedTableModel<Employee>
{

    public EmployeeEnhancedTableModel(ArrayList<Employee> employees)
    {
        super("Employee", new String[]{"ID", "Last Name", "First Name", "Birthdate"}, employees);
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
            case 0 -> ((Model.Employee.Employee) employee).getId();
            case 1 -> ((Model.Employee.Employee) employee).getLastName();
            case 2 -> ((Model.Employee.Employee) employee).getFirstName();
            case 3 -> ((Model.Employee.Employee) employee).getBirthDate();
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return switch (columnIndex) {
            case 0 -> Integer.class;
            case 3 -> java.util.Date.class;
            default -> String.class;
        };
    }
}
