package Model.Item;

import Model.Packaging;
import Model.Vat;

import java.util.Date;

public class Item
{
    private Integer id;
    private String label;
    private float price;
    private int restock_quantity;
    private int current_quantity;
    private int empty_returnable_bottle_quantity;
    private float empty_returnable_bottle_price;
    private Date forecast_date;
    private int forecast_quantity;
    private int min_quantity;
    private Packaging packaging;
    private Vat code_vat;

    public Item(Integer id, String label, float price, int restock_quantity, int current_quantity, int empty_returnable_bottle_quantity, float empty_returnable_bottle_price, Date forecast_date, int forecast_quantity, int min_quantity, Packaging packaging, Vat code_vat)
    {
        this.id = id;
        this.label = label;
        this.price = price;
        this.restock_quantity = restock_quantity;
        this.current_quantity = current_quantity;
        this.empty_returnable_bottle_quantity = empty_returnable_bottle_quantity;
        this.empty_returnable_bottle_price = empty_returnable_bottle_price;
        this.forecast_date = forecast_date;
        this.forecast_quantity = forecast_quantity;
        this.min_quantity = min_quantity;
        this.packaging = packaging;
        this.code_vat = code_vat;
    }

    public Integer getId()
    {
        return id;
    }

    public String getLabel()
    {
        return label;
    }

    public float getPrice()
    {
        return price;
    }

    public int getRestock_quantity()
    {
        return restock_quantity;
    }

    public int getCurrent_quantity()
    {
        return current_quantity;
    }

    public int getEmpty_returnable_bottle_quantity()
    {
        return empty_returnable_bottle_quantity;
    }

    public float getEmpty_returnable_bottle_price()
    {
        return empty_returnable_bottle_price;
    }

    public Date getForecast_date()
    {
        return forecast_date;
    }

    public int getForecast_quantity()
    {
        return forecast_quantity;
    }

    public int getMin_quantity()
    {
        return min_quantity;
    }

    public Packaging getPackaging()
    {
        return packaging;
    }

    public Vat getCode_vat()
    {
        return code_vat;
    }

    @Override
    public String toString()
    {
        return "Item{ \n" +
                "       id=" + id + ", \n" +
                "       label='" + label + '\'' + ", \n" +
                "       price=" + price + ", \n" +
                "       restock_quantity=" + restock_quantity + ", \n" +
                "       current_quantity=" + current_quantity + ", \n" +
                "       empty_returnable_bottle_quantity=" + empty_returnable_bottle_quantity + ", \n" +
                "       empty_returnable_bottle_price=" + empty_returnable_bottle_price + ", \n" +
                "       forecast_date=" + forecast_date + ", \n" +
                "       forecast_quantity=" + forecast_quantity + ", \n" +
                "       min_quantity=" + min_quantity + ", \n" +
                "\n       packaging=" + packaging + ", \n" +
                "       code_vat=" + code_vat + "\n" +
                '}';
    }


}
