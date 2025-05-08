package UI.Process;

import javax.swing.*;
import java.util.ArrayList;

import Controller.Process.ProcessController;
import Exceptions.Process.GetAllProcessesException;
import Model.Customer.Customer;
import Model.CustomerStatus.CustomerStatus;
import Model.Employee.Employee;
import Model.EmployeeStatus.EmployeeStatus;
import Model.Process.Process;
import Model.ProcessStatus.ProcessStatus;
import Model.ProcessType.ProcessType;
import Model.Supplier.Supplier;

import UI.Components.EnhancedTable.JEnhancedTableScrollPanel;
import UI.Components.EnhancedTable.TableModelMaker;
import UI.Models.*;


import Utils.Utils;

public class ReadProcessPanel extends JPanel
{
    public ReadProcessPanel(ProcessPanel processPanel)
    {
        ArrayList<Process> processes;

        try
        {
            processes = ProcessController.getAllProcesses();
        }
        catch (GetAllProcessesException e)
        {
            processes = new ArrayList<>();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        ArrayList<String> menuItems = new ArrayList<>();
        menuItems.add("Update");
        menuItems.add("Delete");

        TableModelMaker tableModelMaker = new TableModelMaker();
        ProcessEnhancedTableModel processTableModel = new ProcessEnhancedTableModel(processes);

        ArrayList<Supplier> suppliers = Utils.transformData(processes, Process::getSupplier);
        SupplierEnhancedTableModel supplierTableModel = new SupplierEnhancedTableModel(suppliers);

        ArrayList<ProcessType> processTypes = Utils.transformData(processes, Process::getType);
        ProcessTypeEnhancedTableModel processTypeTableModel = new ProcessTypeEnhancedTableModel(processTypes);

        ArrayList<ProcessStatus> processStatuses = Utils.transformData(processes, Process::getProcessStatus);
        ProcessStatusEnhancedTableModel processStatusTableModel = new ProcessStatusEnhancedTableModel(processStatuses);

        ArrayList<Employee> employees = Utils.transformData(processes, Process::getEmployee);
        EmployeeEnhancedTableModel employeeTableModel = new EmployeeEnhancedTableModel(employees);

        ArrayList<EmployeeStatus> employeeStatuses = Utils.transformData(employees, Employee::getEmployeeStatus);
        EmployeeStatusEnhancedTableModel employeeStatusTableModel = new EmployeeStatusEnhancedTableModel(employeeStatuses);

        ArrayList<Customer> customers = Utils.transformData(processes, Process::getCustomer);
        CustomerEnhancedTableModel customerTableModel = new CustomerEnhancedTableModel(customers);

        ArrayList<CustomerStatus> customerStatuses = Utils.transformData(customers, Customer::getCustomerStatus);
        CustomerStatusEnhancedTableModel customerStatusTableModel = new CustomerStatusEnhancedTableModel(customerStatuses);

        tableModelMaker.addTableModel(processTableModel);
        tableModelMaker.addTableModel(supplierTableModel);
        tableModelMaker.addTableModel(processTypeTableModel);
        tableModelMaker.addTableModel(processStatusTableModel);
        tableModelMaker.addTableModel(employeeTableModel);
        tableModelMaker.addTableModel(employeeStatusTableModel);
        tableModelMaker.addTableModel(customerTableModel);
        tableModelMaker.addTableModel(customerStatusTableModel);

        JEnhancedTableScrollPanel tableScrollPanel = new JEnhancedTableScrollPanel(tableModelMaker, this);
        add(tableScrollPanel);
        tableModelMaker.setTable(tableScrollPanel);
        tableScrollPanel.updateModel(tableModelMaker);

        ArrayList<Process> finalProcesses = processes;

        tableScrollPanel.addMenuOnRows(menuItems, action ->
        {
            switch (action.getActionCommand())
            {
                case "Update" -> processPanel.moveTo(2);
                case "Delete" -> processPanel.moveTo(3);
            }
            processPanel.notifyObservers(finalProcesses.get(tableScrollPanel.getTable().getSelectedRow()));
        });


    }
}
