package Model.Process;

import Exceptions.Process.ProcessException;
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
import java.util.Objects;

public class MakeProcess
{
    private static final HashMap<Integer, Process> processMap = new HashMap<>();


    public static Process getProcess(   Integer id, String label, Integer number,
                                        Supplier supplier,
                                        ProcessType type,
                                        ProcessStatus processStatus,
                                        Employee employee,
                                        Customer customer)
    {
        int processHash = Process.hashCode(id, label, number, supplier, type, processStatus, employee, customer);

        if(processMap.containsKey(processHash))
        {
            return processMap.get(processHash);
        }
        else
        {
            Process process;
            try
            {
                process = new Process(id, label, number, supplier, type, processStatus, employee, customer);
                processMap.put(processHash, process);
            }
            catch (ProcessException e)
            {
                process = null;
                System.err.println("Error creating Process: " + e.getMessage());
            }
            return process;
        }
    }
}
