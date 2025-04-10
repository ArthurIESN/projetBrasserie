package Model;

import Model.Customer.Customer;

import java.util.Objects;

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
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Cart cart = (Cart) obj;

        return Objects.equals(id, cart.id) &&
                Objects.equals(customer, cart.customer);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, customer);
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
