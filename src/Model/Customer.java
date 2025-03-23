package Model;

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
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.creditLimit = creditLimit;
        this.numVAT = numVAT;
        this.customerStatus = customerStatus;
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

    public float getCreditLimit() {
        return creditLimit;
    }

    public String getNumVAT() {
        return numVAT;
    }

    public CustomerStatus getCustomerStatus() {
        return customerStatus;
    }

    @Override
    public String toString() {
        return id + " - " + lastName + ", " + firstName + " - " + creditLimit + " - " + numVAT + " - " + customerStatus;
    }
}
