package Model;

public class Packaging
{
    private int id;
    private String label;
    private int item_quantity;

    public Packaging(int id, String label, int item_quantity)
    {
        this.id = id;
        this.label = label;
        this.item_quantity = item_quantity;
    }

    public int getId()
    {
        return id;
    }

    public String getLabel()
    {
        return label;
    }

    public int getItem_quantity()
    {
        return item_quantity;
    }


    @Override
    public String toString()
    {
        return "Packaging{ \n" +
                "       id=" + id + ", \n" +
                "       label='" + label + '\n' +
                "       item_quantity=" + item_quantity + "\n" +
                '}';
    }
}
