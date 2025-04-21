package UI.Process;

import Model.Process.Process;

import javax.swing.*;
import java.awt.*;

public class UpdateProcessPanel extends JPanel implements ProcessObserver
{
    private final ProcessModelPanel processModelPanel;
    public UpdateProcessPanel(ProcessPanel processPanel)
    {
        setLayout(new BorderLayout());
        JLabel title = new JLabel("Update a process");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        processPanel.addObserver(this);

        processModelPanel = new ProcessModelPanel(true, true);
        processModelPanel.setButtonText("Update Process");

        processModelPanel.onButtonClicked(e -> JOptionPane.showMessageDialog(null, "Process updated", "Success", JOptionPane.INFORMATION_MESSAGE));

        processModelPanel.onSearchProcessChange(e -> updateProcess());

        add(processModelPanel);
    }

    private void updateProcess()
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
