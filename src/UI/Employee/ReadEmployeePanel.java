package UI.Employee;

import Controller.Employee.EmployeeController;
import Exceptions.Employee.GetAllEmployeesException;
import Model.Employee.Employee;
import Model.EmployeeStatus.EmployeeStatus;
import UI.Components.EnhancedTable.JEnhancedTableScrollPanel;
import UI.Components.EnhancedTable.TableModelMaker;
import UI.Models.*;
import Utils.Utils;

import javax.swing.*;
import java.util.ArrayList;

public class ReadEmployeePanel extends JPanel
{
    public ReadEmployeePanel(EmployeePanel employeePanel)
    {

        ArrayList<Employee> employees = new ArrayList<>();

        try
        {
            employees = EmployeeController.getAllEmployees();
        }
        catch (GetAllEmployeesException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        ArrayList<String> menuItems = new ArrayList<>();
        menuItems.add("Update");
        menuItems.add("Delete");

        TableModelMaker tableModelMaker = new TableModelMaker();
        EmployeeEnhancedTableModel employeeTableModel = new EmployeeEnhancedTableModel(employees);

        ArrayList<EmployeeStatus> employeeStatuses = Utils.transformData(employees, Employee::getEmployeeStatus);
        EmployeeStatusEnhancedTableModel employeeStatusTableModel = new EmployeeStatusEnhancedTableModel(employeeStatuses);

        tableModelMaker.addTableModel(employeeTableModel);
        tableModelMaker.addTableModel(employeeStatusTableModel);

        JEnhancedTableScrollPanel tableScrollPanel = new JEnhancedTableScrollPanel(tableModelMaker, this);
        add(tableScrollPanel);
        tableModelMaker.setTable(tableScrollPanel);
        tableScrollPanel.updateModel(tableModelMaker);

        ArrayList<Employee> finalEmployees = employees;

        tableScrollPanel.addMenuOnRows(menuItems, action ->
        {
            switch (action.getActionCommand())
            {
                case "Update" -> employeePanel.moveTo(2);
                case "Delete" -> employeePanel.moveTo(3);
            }
            employeePanel.notifyObservers(finalEmployees.get(tableScrollPanel.getTable().getSelectedRow()));
        });
    }
}
