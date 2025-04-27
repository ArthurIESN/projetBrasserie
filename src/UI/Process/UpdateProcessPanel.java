package UI.Process;

import Controller.Process.ProcessController;
import Exceptions.Process.UpdateProcessException;
import Model.Customer.Customer;
import Model.Employee.Employee;
import Model.Process.MakeProcess;
import Model.Process.Process;
import Model.ProcessStatus.ProcessStatus;
import Model.ProcessType.ProcessType;
import Model.Supplier.Supplier;

import javax.swing.*;
import java.awt.*;

public class UpdateProcessPanel extends JPanel implements ProcessObserver
{
    private final ProcessModelPanel processModelPanel;
    private final ProcessPanel processPanel;
    public UpdateProcessPanel(ProcessPanel processPanel)
    {
        this.processPanel = processPanel;

        setLayout(new BorderLayout());
        JLabel title = new JLabel("Update a process");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        processPanel.addObserver(this);

        processModelPanel = new ProcessModelPanel(true);
        processModelPanel.setButtonText("Update Process");

        processModelPanel.onButtonClicked(e -> updateProcess());

        processModelPanel.onSearchProcessChange(e -> updateProcessSearch());

        add(processModelPanel);
    }

    private void updateProcess()
    {
        if(processModelPanel.getProcessSearch().getSelectedItem() == null)
        {
            JOptionPane.showMessageDialog(this, "Please select a process to update", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(processModelPanel.isProcessInvalid()) return;

        ProcessType processType = processModelPanel.getTypeSearch().getSelectedItem();
        ProcessStatus processStatus = processModelPanel.getProcessStatusSearch().getSelectedItem();
        Customer customer = processModelPanel.getCustomerSearch().getSelectedItem();
        Supplier supplier = processModelPanel.getSupplierSearch().getSelectedItem();
        Employee employee = processModelPanel.getEmployeeSearch().getSelectedItem();

        // we won't check for customer, supplier and employee, as they are optional
        Process process = MakeProcess.getProcess(   processModelPanel.getProcessIdField().getInt(),
                                                    processModelPanel.getProcessLabelField().getText(),
                                                    processModelPanel.getProcessNumberField().getInt(),
                                                    processModelPanel.getDateField().getDate(),
                                                    supplier,
                                                    processType,
                                                    processStatus,
                                                    employee,
                                                    customer);


        try
        {
            ProcessController.updateProcess(process);
            JOptionPane.showMessageDialog(null, "Process updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

            // refresh
            processPanel.navbarForceClick(2);

        } catch (UpdateProcessException e)
        {
            JOptionPane.showMessageDialog(null, "Error updating process: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateProcessSearch()
    {
        Process selectedProcess = processModelPanel.getProcessSearch().getSelectedItem();
        processModelPanel.getProcessIdField().updateText(String.valueOf(selectedProcess.getId()));
        processModelPanel.getProcessLabelField().updateText(selectedProcess.getLabel());
        processModelPanel.getProcessNumberField().updateText(String.valueOf(selectedProcess.getNumber()));
        processModelPanel.getProcessStatusSearch().forceSetSelectedItem(selectedProcess.getProcessStatus());
        processModelPanel.getTypeSearch().forceSetSelectedItem(selectedProcess.getType());
        processModelPanel.getDateField().setDate(selectedProcess.getCreationDate());

        if(selectedProcess.getCustomer() != null)
            processModelPanel.getCustomerSearch().forceSetSelectedItem(selectedProcess.getCustomer());
        else
            processModelPanel.getCustomerSearch().forceSetSelectedItem(null);

        if(selectedProcess.getSupplier() != null)
            processModelPanel.getSupplierSearch().forceSetSelectedItem(selectedProcess.getSupplier());
        else
            processModelPanel.getSupplierSearch().forceSetSelectedItem(null);

        if(selectedProcess.getEmployee() != null)
            processModelPanel.getEmployeeSearch().forceSetSelectedItem(selectedProcess.getEmployee());
        else
            processModelPanel.getEmployeeSearch().forceSetSelectedItem(null);
    }

    @Override
    public void update(Process process)
    {
        processModelPanel.getProcessSearch().setSelectedItem(process);
    }
}
