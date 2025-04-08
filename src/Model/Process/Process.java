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
    private Date creationDate;
    private Supplier supplier;
    private ProcessType type;
    private ProcessStatus processStatus;
    private Employee employee;
    private Customer customer;

    public Process(Integer id, String label, int number, Date creationDate, Supplier supplier, ProcessType type, ProcessStatus processStatus,
                   Employee employee, Customer customer)
    {
        this.id = id;
        this.label = label;
        this.number = number;
        this.creationDate = creationDate;
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

    public Date getCreationDate() {
        return creationDate;
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
    public String toString(){
        return id + " - " + label + " - " + number +  " - " + creationDate + " - " + supplier + " - " + type + " - " + processStatus + " - " + employee + " - " + customer;
    }

}
