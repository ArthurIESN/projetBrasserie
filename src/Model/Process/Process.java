package Model.Process;

import Model.Customer.Customer;
import Model.Employee.Employee;
import Model.ProcessStatus.ProcessStatus;
import Model.Supplier.Supplier;
import Model.Type;

public class Process {
    private Integer id;
    private String label;
    private int number;
    private Supplier supplier;
    private Type type;
    private ProcessStatus processStatus;
    private Employee employee;
    private Customer customer;

    public Process(Integer id, String label, int number, Supplier supplier, Type type, ProcessStatus processStatus,
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

    public Supplier getSupplier() {
        return supplier;
    }

    public Type getType() {
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
    public String toString(){
        return id + " - " + label + " - " + number + " - " + supplier + " - " + type + " - " + processStatus + " - " + employee + " - " + customer;
    }

}
