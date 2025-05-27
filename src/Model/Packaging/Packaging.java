package Model.Packaging;

import Exceptions.Packaging.PackagingException;

import java.util.Objects;

public class Packaging
{
    private Integer id;
    private String label;
    private int quantity;

    public Packaging(Integer id, String label, int quantity) throws PackagingException
    {
        setId(id);
        setLabel(label);
        setQuantity(quantity);
    }

    public Integer getId()
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

    public void setId(Integer id) throws PackagingException{
        if(id == null || id <= 0)
        {
            throw new PackagingException("ID cannot be null or less than or equal to 0");
        }
        this.id = id;
    }

    public void setLabel(String label) throws PackagingException
    {
        if(label == null || label.isEmpty())
        {
            throw new PackagingException("Label cannot be null or empty");
        }
        this.label = label;
    }

    public void setQuantity(int quantity) throws PackagingException
    {
        if(quantity < 0)
        {
            throw new PackagingException("Quantity cannot be negative");
        }
        this.quantity = quantity;
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
