package Model;

import Model.Country.Country;

import java.util.Objects;

public class Locality
{
    private final Integer id;
    private final String address;
    private final String postalCode;
    private final String number;
    private final Country country;

    public Locality(Integer id, String address, String postalCode, String number, Country country)
    {
        this.id = id;
        this.address = address;
        this.postalCode = postalCode;
        this.number = number;
        this.country = country;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Locality locality = (Locality) obj;

        return Objects.equals(id, locality.id) &&
                Objects.equals(address, locality.address) &&
                Objects.equals(postalCode, locality.postalCode) &&
                Objects.equals(number, locality.number) &&
                Objects.equals(country, locality.country);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, address, postalCode, number, country);
    }

    @Override
    public String toString()
    {
        return "Locality{" +
                "id=" + id + "\n" +
                ", address='" + address + '\n' +
                ", postalCode='" + postalCode + '\n' +
                ", number='" + number + '\n' +
                ", country=" + country + '\n' +
                '}';
    }
}
