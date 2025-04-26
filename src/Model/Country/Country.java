package Model.Country;

import java.util.Objects;

public class Country
{
    private final Integer id;
    private final String name;
    private final float deliveryCost;

    public Country(Integer id, String name, float deliveryCost)
    {
        this.id = id;
        this.name = name;
        this.deliveryCost = deliveryCost;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Country country = (Country) obj;

        return Objects.equals(id, country.id) &&
                Objects.equals(name, country.name) &&
                Objects.equals(deliveryCost, country.deliveryCost);
    }

    public static int hashCode(Integer id, String name, float deliveryCost)
    {
        return Objects.hash(id, name, deliveryCost);
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, deliveryCost);
    }

    @Override
    public String toString()
    {
        return "Country{" +
                "id=" + id + "\n" +
                ", name='" + name + '\n' +
                ", deliveryCost=" + deliveryCost + '\n' +
                '}';
    }

}
