package Model.Locality;

import Exceptions.Locality.LocalityException;
import Model.Country.Country;

import java.util.Objects;

public class Locality
{
    private Integer id;
    private String address;
    private String postalCode;
    private String number;
    private Country country;

    public Locality(Integer id, String address, String postalCode, String number, Country country) throws LocalityException
    {
        setId(id);
        setAddress(address);
        setPostalCode(postalCode);
        setNumber(number);
        setCountry(country);
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id) throws LocalityException
    {
        if(id == null || id <= 0)
        {
            throw new LocalityException("ID cannot be null or less than or equal to 0");
        }
        this.id = id;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address) throws LocalityException
    {
        if(address == null || address.isEmpty())
        {
            throw new LocalityException("Address cannot be null or empty");
        }
        this.address = address;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(String postalCode) throws LocalityException
    {
        if(postalCode == null || postalCode.isEmpty())
        {
            throw new LocalityException("Postal code cannot be null or empty");
        }
        this.postalCode = postalCode;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number) throws LocalityException
    {
        if(number == null || number.isEmpty())
        {
            throw new LocalityException("Number cannot be null or empty");
        }
        this.number = number;
    }

    public Country getCountry()
    {
        return country;
    }

    public void setCountry(Country country) throws LocalityException
    {
        if(country == null)
        {
            throw new LocalityException("Country cannot be null");
        }
        this.country = country;
    }

    public static int hashCode(Integer id, String address, String postalCode, String number, Country country)
    {
        return Objects.hash(id, address, postalCode, number, country);
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
