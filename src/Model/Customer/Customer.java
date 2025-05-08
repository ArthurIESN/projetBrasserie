package Model.Customer;

import Model.CustomerStatus.CustomerStatus;

import java.util.Objects;

public class Customer {
    private Integer id;
    private String lastName;
    private String firstName;
    private float creditLimit;
    private String numVAT;
    private CustomerStatus customerStatus;

    public Customer(Integer id, String lastName, String firstName, float creditLimit, String numVAT,
                    CustomerStatus customerStatus)
    {
        setId(id);
        this.lastName = lastName;
        this.firstName = firstName;
        this.creditLimit = creditLimit;
        this.numVAT = numVAT;
        this.customerStatus = customerStatus;
    }

    private void setId(Integer id)
    {
        if(id == null || id <= 0)
        {
            id = null;
        }
        else
        {
            this.id = id;
        }
    }

    public Integer getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public float getCreditLimit() {
        return creditLimit;
    }

    public String getNumVAT() {
        return numVAT;
    }

    public CustomerStatus getCustomerStatus() {
        return customerStatus;
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
