package Model;

import Model.Customer.Customer;

public class Cart
{
    private final Integer id;
    private final Customer customer;

    public Cart(Integer id, Customer customer)
    {
        this.id = id;
        this.customer = customer;
    }

    @Override
    public String toString()
    {
        return "Cart{" +
                "id=" + id + "\n" +
                ", customer=" + customer + '\n' +
                '}';
    }
}
