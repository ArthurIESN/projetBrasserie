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
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Packaging packaging = (Packaging) obj;

        return Objects.equals(id, packaging.id) &&
                Objects.equals(label, packaging.label) &&
                Objects.equals(quantity, packaging.quantity);
    }

    public static int hashCode(int id, String label, int quantity)
    {
        return Objects.hash(id, label, quantity);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, label, quantity);
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
