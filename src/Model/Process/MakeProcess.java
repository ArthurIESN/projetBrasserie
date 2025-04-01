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


    public static Process getProcess(   Integer id, String label, Integer number, Date creationDate,
                                        Supplier supplier,
                                        ProcessType type,
                                        ProcessStatus processStatus,
                                        Employee employee,
                                        Customer customer)
    {
        if(processMap.containsKey(id))
        {
            return processMap.get(id);
        }
        else
        {
            Process process = new Process(id, label, number, creationDate, supplier, type, processStatus, employee, customer);
            processMap.put(id, process);

            return process;
        }
    }
}
