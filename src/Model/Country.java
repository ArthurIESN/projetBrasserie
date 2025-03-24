package Model;

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
    public String toString()
    {
        return "Country{" +
                "id=" + id + "\n" +
                ", name='" + name + '\n' +
                ", deliveryCost=" + deliveryCost + '\n' +
                '}';
    }

}
