package Model.Process;

import Exceptions.Process.ProcessException;
import Model.Customer.Customer;
import Model.Employee.Employee;
import Model.ProcessStatus.ProcessStatus;
import Model.Supplier.Supplier;
import Model.ProcessType.ProcessType;

import java.util.Objects;

public class Process {
    private Integer id;
    private String label;
    private int number;
    private final Supplier supplier;
    private ProcessType type;
    private ProcessStatus processStatus;
    private final Employee employee;
    private final Customer customer;

    public Process(Integer id, String label, int number, Supplier supplier, ProcessType type, ProcessStatus processStatus,
                   Employee employee, Customer customer) throws ProcessException
    {
        setId(id);
        setLabel(label);
        setNumber(number);
        this.supplier = supplier;
        setType(type);
        setProcessStatus(processStatus);
        this.employee = employee;
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }


    public String getLabel() {
        return label;
    }

    public int getNumber() {
        return number;
    }

    public void setId(Integer id) throws ProcessException {
        if(id == null || id <= 0){
            throw new ProcessException("ID cannot be null or less than or equal to 0");
        }
        this.id = id;
    }

    public void setNumber(int number) throws ProcessException
    {
        if(number < 0)
        {
            throw new ProcessException("Number cannot be less than 0");
        }
        this.number = number;
    }

    public void setLabel(String message) throws ProcessException
    {
        if(message == null || message.isEmpty())
        {
            throw new ProcessException("Label cannot be null or empty");
        }
        this.label = message;
    }

    public void setType(ProcessType type) throws ProcessException
    {
        if(type == null)
        {
            throw new ProcessException("Type cannot be null");
        }
        this.type = type;
    }

    public void setProcessStatus(ProcessStatus processStatus) throws ProcessException
    {
        if(processStatus == null)
        {
            throw new ProcessException("Process status cannot be null");
        }
        this.processStatus = processStatus;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public ProcessType getType() {
        return type;
    }

    public ProcessStatus getProcessStatus() {
        return processStatus;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Customer getCustomer() {
        return customer;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        System.out.println("this : " + this);

        Process process = (Process) obj;
        System.out.println("Process " + process);
        return Objects.equals(id, process.id) &&
                Objects.equals(label, process.label) &&
                Objects.equals(number, process.number) &&
                Objects.equals(supplier, process.supplier) &&
                Objects.equals(type, process.type) &&
                Objects.equals(processStatus, process.processStatus) &&
                Objects.equals(employee, process.employee) &&
                Objects.equals(customer, process.customer);
    }

    public static int hashCode(Integer id, String label, int number,Supplier supplier,
                                 ProcessType type, ProcessStatus processStatus, Employee employee, Customer customer)
    {
        return Objects.hash(id, label, number, supplier, type, processStatus, employee, customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, number, supplier, type, processStatus, employee, customer);
    }

    @Override
    public String toString(){
        return id + " - " + label + " - " + number  + " - " + supplier + " - " + type + " - " + processStatus + " - " + employee + " - " + customer;
    }

}
