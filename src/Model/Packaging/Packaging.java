package Model.Packaging;

import java.util.Objects;

public class Packaging
{
    private int id;
    private String label;
    private int quantity;

    public Packaging(int id, String label, int quantity)
    {
        this.id = id;
        this.label = label;
        this.quantity = quantity;
    }

    public int getId()
    {
        return id;
    }

    public String getLabel()
    {
        return label;
    }

    public int getQuantity()
    {
        return quantity;
    }

    @Override
    public String toString()
    {
        return "Packaging{ \n" +
                "       id=" + id + ", \n" +
                "       label='" + label + '\n' +
                "       quantity=" + quantity + "\n" +
                '}';
    }
}
