package UI.Process;

import Controller.Process.ProcessController;
import Exceptions.Access.UnauthorizedAccessException;
import Exceptions.Process.DeleteProcessException;
import Exceptions.Process.GetAllProcessesException;
import Model.Customer.Customer;
import Model.CustomerStatus.CustomerStatus;
import Model.Employee.Employee;
import Model.EmployeeStatus.EmployeeStatus;
import Model.Process.Process;
import Model.ProcessStatus.ProcessStatus;
import Model.ProcessType.ProcessType;
import Model.Supplier.Supplier;
import UI.Components.GridBagLayoutHelper;
import UI.Components.EnhancedTable.JEnhancedTableScrollPanel;
import UI.Components.Fields.SearchListPanel;
import UI.Components.EnhancedTable.TableModelMaker;
import UI.Models.*;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DeleteProcessPanel extends JPanel implements ProcessObserver
{
    private final SearchListPanel<Process> processSearch;
    public DeleteProcessPanel(ProcessPanel processPanel)
    {
        GridBagLayoutHelper gridDeleteProcess = new GridBagLayoutHelper();

        ArrayList<Process> processes = new ArrayList<>();

        try
        {
            processes = ProcessController.getAllProcesses();
        }
        catch (GetAllProcessesException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        processPanel.addObserver(this);

        processSearch = new SearchListPanel<>( processes, searchProcess -> searchProcess.getLabel() + " - " + searchProcess.getNumber() + " - " + searchProcess.getProcessStatus().getLabel());
        processSearch.setPreferredSize(new Dimension(500, processSearch.getPreferredSize().height));
        processSearch.getSearchField().setPlaceholder("Search for a process");
        gridDeleteProcess.addField("Process", processSearch);

        JButton deleteButton = getJButton(processPanel);

        gridDeleteProcess.addField(deleteButton);

        add(gridDeleteProcess, BorderLayout.CENTER);

        TableModelMaker tableModelMaker = new TableModelMaker();
        ProcessEnhancedTableModel processTableModel = new ProcessEnhancedTableModel();
        SupplierEnhancedTableModel supplierTableModel = new SupplierEnhancedTableModel();
        ProcessTypeEnhancedTableModel processTypeTableModel = new ProcessTypeEnhancedTableModel();
        ProcessStatusEnhancedTableModel processStatusTableModel = new ProcessStatusEnhancedTableModel();
        EmployeeEnhancedTableModel employeeTableModel = new EmployeeEnhancedTableModel();
        EmployeeStatusEnhancedTableModel employeeStatusTableModel = new EmployeeStatusEnhancedTableModel();
        CustomerEnhancedTableModel customerTableModel = new CustomerEnhancedTableModel();
        CustomerStatusEnhancedTableModel customerStatusTableModel = new CustomerStatusEnhancedTableModel();

        tableModelMaker.addTableModel(processTableModel);
        tableModelMaker.addTableModel(supplierTableModel);
        tableModelMaker.addTableModel(processTypeTableModel);
        tableModelMaker.addTableModel(processStatusTableModel);
        tableModelMaker.addTableModel(employeeTableModel);
        tableModelMaker.addTableModel(employeeStatusTableModel);
        tableModelMaker.addTableModel(customerTableModel);
        tableModelMaker.addTableModel(customerStatusTableModel);

        JEnhancedTableScrollPanel tableScrollPanel = new JEnhancedTableScrollPanel(tableModelMaker, this, 4);
        tableModelMaker.setTable(tableScrollPanel);

        processSearch.onSelectedItemChange(e ->
        {
            Process selectedProcess = processSearch.getSelectedItem();

            ArrayList<Process> processes1 = new ArrayList<>();
            processes1.add(selectedProcess);
            ArrayList<Supplier> suppliers = Utils.transformData(selectedProcess, Process::getSupplier);
            ArrayList<ProcessType> processTypes = Utils.transformData(selectedProcess, Process::getType);
            ArrayList<ProcessStatus> processStatuses = Utils.transformData(selectedProcess, Process::getProcessStatus);
            ArrayList<Employee> employees = Utils.transformData(selectedProcess, Process::getEmployee);
            ArrayList<EmployeeStatus> employeeStatuses = Utils.transformData(employees, Employee::getEmployeeStatus);
            ArrayList<Customer> customers = Utils.transformData(selectedProcess, Process::getCustomer);
            ArrayList<CustomerStatus> customerStatuses = Utils.transformData(customers, Customer::getCustomerStatus);

            processTableModel.setData(processes1);
            supplierTableModel.setData(suppliers);
            processTypeTableModel.setData(processTypes);
            processStatusTableModel.setData(processStatuses);
            employeeTableModel.setData(employees);
            employeeStatusTableModel.setData(employeeStatuses);
            customerTableModel.setData(customers);
            customerStatusTableModel.setData(customerStatuses);

            tableModelMaker.setTable(tableScrollPanel);

            if (selectedProcess != null)
            {
                tableScrollPanel.updateModel(tableModelMaker);
            }
        });
        add(tableScrollPanel);
    }

    private JButton getJButton(ProcessPanel processPanel) {
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e ->
        {

            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this process?", "Warning", JOptionPane.YES_NO_OPTION);

            if(dialogResult == JOptionPane.NO_OPTION)
            {
                return;
            }

            Process selectedProcess = processSearch.getSelectedItem();
            if (selectedProcess != null)
            {
                try
                {
                    ProcessController.deleteProcess(selectedProcess.getId());

                    JOptionPane.showMessageDialog(null, "Process deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

                    // refresh the panel
                    processPanel.navbarForceClick(3);

                }
                catch (DeleteProcessException | UnauthorizedAccessException e1)
                {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        return deleteButton;
    }

    @Override
    public void update(Process process)
    {
        processSearch.forceSetSelectedItem(process);
    }

}
