package Model.Item;

import Model.Packaging.Packaging;
import Model.Vat.Vat;

import java.util.Date;
import java.util.Objects;

public class Item
{
    private Integer id;
    private String label;
    private float price;
    private int restockQuantity;
    private int currentQuantity;
    private int emptyReturnableBottleQuantity;
    private float emptyReturnableBottlePrice;
    private Date forecastDate;
    private int forecastQuantity;
    private int minQuantity;
    private Packaging packaging;
    private Vat vat;

    public Item(Integer id, String label, float price, int restockQuantity, int currentQuantity, int emptyReturnableBottleQuantity, float emptyReturnableBottlePrice, Date forecastDate, int forecastQuantity, int minQuantity, Packaging packaging, Vat vat)
    {
        this.id = id;
        this.label = label;
        this.price = price;
        this.restockQuantity = restockQuantity;
        this.currentQuantity = currentQuantity;
        this.emptyReturnableBottleQuantity = emptyReturnableBottleQuantity;
        this.emptyReturnableBottlePrice = emptyReturnableBottlePrice;
        this.forecastDate = forecastDate;
        this.forecastQuantity = forecastQuantity;
        this.minQuantity = minQuantity;
        this.packaging = packaging;
        this.vat = vat;
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

    public int getRestockQuantity()
    {
        return restockQuantity;
    }

    public int getCurrentQuantity()
    {
        return currentQuantity;
    }

    public int getEmptyReturnableBottleQuantity()
    {
        return emptyReturnableBottleQuantity;
    }

    public float getEmptyReturnableBottlePrice()
    {
        return emptyReturnableBottlePrice;
    }

    public Date getForecastDate()
    {
        return forecastDate;
    }

    public int getForecastQuantity()
    {
        return forecastQuantity;
    }

    public int getMinQuantity()
    {
        return minQuantity;
    }

    public Packaging getPackaging()
    {
        return packaging;
    }

    public Vat getVat()
    {
        return vat;
    }

    public void setVat(Vat vat)
    {
        this.vat = vat;
    }

    @Override
    public String toString()
    {
        return "Item{ \n" +
                "       id=" + id + ", \n" +
                "       label='" + label + '\'' + ", \n" +
                "       price=" + price + ", \n" +
                "       restock_quantity=" + restockQuantity + ", \n" +
                "       current_quantity=" + currentQuantity + ", \n" +
                "       empty_returnable_bottle_quantity=" + emptyReturnableBottleQuantity + ", \n" +
                "       empty_returnable_bottle_price=" + emptyReturnableBottlePrice + ", \n" +
                "       forecast_date=" + forecastDate + ", \n" +
                "       forecast_quantity=" + forecastQuantity + ", \n" +
                "       min_quantity=" + minQuantity + ", \n" +
                "\n       packaging=" + packaging + ", \n" +
                "       code_vat=" + vat + "\n" +
                '}';
    }


}
