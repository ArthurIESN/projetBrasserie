package Model;

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
