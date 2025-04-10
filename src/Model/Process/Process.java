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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Process process = (Process) obj;

        return Objects.equals(id, process.id) &&
                Objects.equals(label, process.label) &&
                Objects.equals(number, process.number) &&
                Objects.equals(creationDate, process.creationDate) &&
                Objects.equals(supplier, process.supplier) &&
                Objects.equals(type, process.type) &&
                Objects.equals(processStatus, process.processStatus) &&
                Objects.equals(employee, process.employee) &&
                Objects.equals(customer, process.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, number, creationDate, supplier, type, processStatus, employee, customer);
    }

    @Override
    public String toString(){
        return id + " - " + label + " - " + number +  " - " + creationDate + " - " + supplier + " - " + type + " - " + processStatus + " - " + employee + " - " + customer;
    }

}
