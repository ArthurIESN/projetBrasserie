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
        processModelPanel.getProcessStatusSearch().setSelectedItem(selectedProcess.getProcessStatus());
        processModelPanel.getTypeSearch().setSelectedItem(selectedProcess.getType());
        processModelPanel.getDateField().setDate(selectedProcess.getCreationDate());

        if(selectedProcess.getCustomer() != null)
            processModelPanel.getCustomerSearch().setSelectedItem(selectedProcess.getCustomer());
        else
            processModelPanel.getCustomerSearch().setSelectedItem(null);

        if(selectedProcess.getSupplier() != null)
            processModelPanel.getSupplierSearch().setSelectedItem(selectedProcess.getSupplier());
        else
            processModelPanel.getSupplierSearch().setSelectedItem(null);

        if(selectedProcess.getEmployee() != null)
            processModelPanel.getEmployeeSearch().setSelectedItem(selectedProcess.getEmployee());
        else
            processModelPanel.getEmployeeSearch().setSelectedItem(null);
    }

    @Override
    public void update(Object object)
    {
        if(object instanceof Process process)
        {
            processModelPanel.getProcessSearch().setSelectedItem(process);
        }
    }
}
