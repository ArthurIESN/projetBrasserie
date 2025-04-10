package Model;

import Model.Item.Item;

import java.util.Objects;

public class CartLineItem
{
    private final Integer id;
    private final CartLine cartLine;
    private final Item item;

    public CartLineItem(Integer id, CartLine cartLine, Item item)
    {
        this.id = id;
        this.cartLine = cartLine;
        this.item = item;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        CartLineItem that = (CartLineItem) obj;

        return Objects.equals(id, that.id) &&
                Objects.equals(cartLine, that.cartLine) &&
                Objects.equals(item, that.item);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, cartLine, item);
    }

    @Override
    public String toString()
    {
        return "CartLineItem{" +
                "id=" + id + "\n" +
                ", cartLine=" + cartLine + "\n" +
                ", item=" + item + "\n" +
                '}';
    }
}
