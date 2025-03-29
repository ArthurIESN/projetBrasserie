package Model.Process;

import Model.Customer.Customer;
import Model.Customer.MakeCustomer;
import Model.ProcessStatus.MakeProcessStatus;
import Model.ProcessStatus.ProcessStatus;
import Model.Supplier.MakeSupplier;
import Model.Supplier.Supplier;
import Model.ProcessType.ProcessType;
import Model.ProcessType.MakeProcessType;
import Model.Employee.Employee;
import Model.Employee.MakeEmployee;

import java.util.Date;
import java.util.HashMap;

public class MakeProcess
{
    private static final HashMap<Integer, Process> processMap = new HashMap<>();

    public static Process getProcess(Integer id, String label, Integer number,
                                     Integer supplierId, String supplierName,
                                     Integer typeId, String typeLabel,
                                     Integer processStatusId, String processStatusLabel,
                                     Integer employeeId, String employeeLastName, String employeeFirstName, Date birthDate, Integer employeeStatusId, String employeeStatusLabel,
                                     Integer customerId, String customerLastName, String customerFirstName, float customerCreditLimit, String customerNumVat, Integer customerStatusId, String customerStatusLabel
                                    )
    {
        if(processMap.containsKey(id))
        {
            return processMap.get(id);
        }
        else
        {

            ProcessType type = MakeProcessType.getType(typeId, typeLabel);
            ProcessStatus processStatus = MakeProcessStatus.getProcessStatus(processStatusId, processStatusLabel);
            Supplier supplier;
            Employee employee;
            Customer customer;

            if(supplierId == null)
            {
                supplier = null;
            }
            else
            {
                supplier = MakeSupplier.getSupplier(supplierId, supplierName);
            }

            if(employeeId == null)
            {
                employee = null;
            }
            else
            {
                employee = MakeEmployee.getEmployee(employeeId, employeeLastName, employeeFirstName, birthDate, employeeStatusId, employeeStatusLabel);
            }

            if(customerId == null)
            {
                customer = null;
            }
            else
            {
                customer = MakeCustomer.getCustomer(customerId, customerLastName, customerFirstName, customerCreditLimit, customerNumVat, customerStatusId, customerStatusLabel);
            }

            Process process = new Process(id, label, number, supplier, type, processStatus, employee, customer);
            processMap.put(id, process);

            return process;
        }
    }
}
