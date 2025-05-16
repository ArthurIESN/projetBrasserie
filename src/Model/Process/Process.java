package Model.Process;

import Model.Customer.Customer;
import Model.Employee.Employee;
import Model.ProcessStatus.ProcessStatus;
import Model.Supplier.Supplier;
import Model.ProcessType.ProcessType;

import java.util.Date;
import java.util.Objects;

public class Process {
    private Integer id;
    private String label;
    private int number;
    private Supplier supplier;
    private ProcessType type;
    private ProcessStatus processStatus;
    private Employee employee;
    private Customer customer;

    public Process(Integer id, String label, int number, Supplier supplier, ProcessType type, ProcessStatus processStatus,
                   Employee employee, Customer customer)
    {
        this.id = id;
        this.label = label;
        this.number = number;
        this.supplier = supplier;
        this.type = type;
        this.processStatus = processStatus;
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

    public void setNumber(int number)
    {
        if(number < 0)
        {
            this.number = number;
        }
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
