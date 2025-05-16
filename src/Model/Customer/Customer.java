package Model.Customer;

import Exceptions.Customer.CustomerException;
import Model.CustomerStatus.CustomerStatus;

import java.util.Objects;

public class Customer
{
    private Integer id;
    private String lastName;
    private String firstName;
    private float creditLimit;
    private String numVAT;
    private CustomerStatus customerStatus;

    public Customer(Integer id, String lastName, String firstName, float creditLimit, String numVAT,
                    CustomerStatus customerStatus) throws CustomerException
    {
        setId(id);
        setLastName(lastName);
        setFirstName(firstName);
        setCreditLimit(creditLimit);
        setNumVAT(numVAT);
        setCustomerStatus(customerStatus);
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id) throws CustomerException
    {
        if(id == null || id <= 0)
        {
            throw new CustomerException("ID must be a positive integer");
        }

        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName)  throws CustomerException
    {
        if(lastName == null || lastName.isEmpty())
        {
            throw new CustomerException("Last name cannot be null or empty");
        }

        this.lastName = lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName) throws CustomerException
    {
        if(firstName == null || firstName.isEmpty())
        {
            throw new CustomerException("First name cannot be null or empty");
        }

        this.firstName = firstName;
    }

    public String getFullName()
    {
        return firstName + " " + lastName;
    }

    public float getCreditLimit()
    {
        return creditLimit;
    }

    public void setCreditLimit(float creditLimit) throws CustomerException
    {
        if(creditLimit < 0)
        {
            throw new CustomerException("Credit limit cannot be negative");
        }

        this.creditLimit = creditLimit;
    }

    public String getNumVAT()
    {
        return numVAT;
    }

    public void setNumVAT(String numVAT) throws CustomerException
    {
        if(numVAT == null || numVAT.isEmpty())
        {
            throw new CustomerException("VAT number cannot be null or empty");
        }

        this.numVAT = numVAT;
    }

    public CustomerStatus getCustomerStatus()
    {
        return customerStatus;
    }

    public void setCustomerStatus(CustomerStatus customerStatus) throws CustomerException
    {
        if(customerStatus == null)
        {
            throw new CustomerException("Customer status cannot be null");
        }

        this.customerStatus = customerStatus;
    }

    // equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Customer customer = (Customer) obj;

        return  Objects.equals(id, customer.id) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(firstName, customer.firstName) &&
                Objects.equals(creditLimit, customer.creditLimit) &&
                Objects.equals(numVAT, customer.numVAT) &&
                Objects.equals(customerStatus, customer.customerStatus);
    }

    public static int hashCode(Integer id, String lastName, String firstName, float creditLimit,
            String numVAT, CustomerStatus customerStatus)
    {
        return Objects.hash(id, lastName, firstName, creditLimit, numVAT, customerStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, creditLimit, numVAT, customerStatus);
    }

    @Override
    public String toString() {
        return id + " - " + lastName + ", " + firstName + " - " + creditLimit + " - " + numVAT + " - " + customerStatus;
    }
}
