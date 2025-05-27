package Model;

import java.util.Objects;

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
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        CartLine cartLine = (CartLine) obj;

        return Objects.equals(id, cartLine.id) &&
                Objects.equals(quantity, cartLine.quantity) &&
                Objects.equals(cart, cartLine.cart);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, quantity, cart);
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
