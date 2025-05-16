package Model.Country;

import Exceptions.Customer.CountryException;

import java.util.Objects;

public class Country
{
    private Integer id;
    private String label;
    private float deliveryCost;

    public Country(Integer id, String name, float deliveryCost) throws CountryException
    {
        setId(id);
        setLabel(name);
        setDeliveryCost(deliveryCost);
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id) throws CountryException
    {
        if(id == null || id <= 0)
        {
            throw new CountryException("ID must be a positive integer");
        }

        this.id = id;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label) throws CountryException
    {
        if(label == null || label.isEmpty())
        {
            throw new CountryException("Name cannot be null or empty");
        }

        this.label = label;
    }

    public float getDeliveryCost()
    {
        return deliveryCost;
    }

    public void setDeliveryCost(float deliveryCost) throws CountryException
    {
        if(deliveryCost < 0)
        {
            throw new CountryException("Delivery cost cannot be negative");
        }

        this.deliveryCost = deliveryCost;
    }


    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Country country = (Country) obj;

        return Objects.equals(id, country.id) &&
                Objects.equals(label, country.label) &&
                Objects.equals(deliveryCost, country.deliveryCost);
    }

    public static int hashCode(Integer id, String name, float deliveryCost)
    {
        return Objects.hash(id, name, deliveryCost);
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(id, label, deliveryCost);
    }

    @Override
    public String toString()
    {
        return "Country{" +
                "id=" + id + "\n" +
                ", name='" + label + '\n' +
                ", deliveryCost=" + deliveryCost + '\n' +
                '}';
    }

}
