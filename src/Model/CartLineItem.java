package Model;

import Model.Item.Item;

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
    public String toString()
    {
        return "CartLineItem{" +
                "id=" + id + "\n" +
                ", cartLine=" + cartLine + "\n" +
                ", item=" + item + "\n" +
                '}';
    }
}
