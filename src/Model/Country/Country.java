package Model.Country;

import java.util.Objects;

public class Country
{
    private Integer id;
    private final String label;
    private final float deliveryCost;

    public Country(Integer id, String name, float deliveryCost)
    {
        setId(id);
        this.label = name;
        this.deliveryCost = deliveryCost;
    }

    public Integer getId()
    {
        return id;
    }

    public String getLabel()
    {
        return label;
    }

    public float getDeliveryCost()
    {
        return deliveryCost;
    }

    private void setId(Integer id)
    {
        if(id == null || id <= 0)
        {
            id = null;
        }
        else
        {
            this.id = id;
        }
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
