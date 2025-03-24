package Model;

public class CartLine
{
    private final Integer id;
    private final int quantity;
    private final Cart cart;

    public CartLine(Integer id, int quantity, Cart cart)
    {
        this.id = id;
        this.quantity = quantity;
        this.cart = cart;
    }

    @Override
    public String toString()
    {
        return "CartLine{" +
                "id=" + id + "\n" +
                ", quantity=" + quantity + "\n" +
                ", cart=" + cart + "\n" +
                '}';
    }
}
