package Model.Item;

import Exceptions.Item.ItemException;
import Model.Event.Event;
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

    public Item(Integer id, String label, float price, int restockQuantity, int currentQuantity, int emptyReturnableBottleQuantity, float emptyReturnableBottlePrice, Date forecastDate, int forecastQuantity, int minQuantity, Packaging packaging, Vat vat) throws ItemException
    {
        setId(id);
        setLabel(label);
        setPrice(price);
        setRestockQuantity(restockQuantity);
        setCurrentQuantity(currentQuantity);
        setEmptyReturnableBottleQuantity(emptyReturnableBottleQuantity);
        setEmptyReturnableBottlePrice(emptyReturnableBottlePrice);
        setForecastDate(forecastDate);
        setForecastQuantity(forecastQuantity);
        setMinQuantity(minQuantity);
        setPackaging(packaging);
        setVat(vat);
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id) throws ItemException
    {
        if(id == null || id <= 0)
        {
            throw new ItemException("ID cannot be null or less than or equal to 0");
        }
        this.id = id;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label) throws ItemException
    {
        if(label == null || label.isEmpty())
        {
            throw new ItemException("Label cannot be null or empty");
        }
        this.label = label;
    }

    public float getPrice()
    {
        return price;
    }

    public void setPrice(float price) throws ItemException
    {
        if(price < 0)
        {
            throw new ItemException("Price cannot be less than 0");
        }
        this.price = price;
    }

    public int getRestockQuantity()
    {
        return restockQuantity;
    }

    public void setRestockQuantity(int restockQuantity) throws ItemException
    {
        if(restockQuantity < 0)
        {
            throw new ItemException("Restock quantity cannot be less than 0");
        }
        this.restockQuantity = restockQuantity;
    }

    public int getCurrentQuantity()
    {
        return currentQuantity;
    }

    public void setCurrentQuantity(int currentQuantity) throws ItemException
    {
        if(currentQuantity < 0)
        {
            throw new ItemException("Current quantity cannot be less than 0");
        }
        this.currentQuantity = currentQuantity;
    }

    public int getEmptyReturnableBottleQuantity()
    {
        return emptyReturnableBottleQuantity;
    }

    public void setEmptyReturnableBottleQuantity(int emptyReturnableBottleQuantity) throws ItemException
    {
        if(emptyReturnableBottleQuantity < 0)
        {
            throw new ItemException("Empty returnable bottle quantity cannot be less than 0");
        }
        this.emptyReturnableBottleQuantity = emptyReturnableBottleQuantity;
    }

    public float getEmptyReturnableBottlePrice()
    {
        return emptyReturnableBottlePrice;
    }

    public void setEmptyReturnableBottlePrice(float emptyReturnableBottlePrice) throws ItemException
    {
        if(emptyReturnableBottlePrice < 0)
        {
            throw new ItemException("Empty returnable bottle price cannot be less than 0");
        }
        this.emptyReturnableBottlePrice = emptyReturnableBottlePrice;
    }

    public Date getForecastDate()
    {
        return forecastDate;
    }

    public void setForecastDate(Date forecastDate) throws ItemException
    {
        if(forecastDate == null)
        {
            throw new ItemException("Forecast date cannot be null");
        }
        this.forecastDate = forecastDate;
    }

    public int getForecastQuantity()
    {
        return forecastQuantity;
    }

    public void setForecastQuantity(int forecastQuantity) throws ItemException
    {
        if(forecastQuantity < 0)
        {
            throw new ItemException("Forecast quantity cannot be less than 0");
        }
        this.forecastQuantity = forecastQuantity;
    }

    public int getMinQuantity()
    {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) throws ItemException
    {
        if(minQuantity < 0)
        {
            throw new ItemException("Minimum quantity cannot be less than 0");
        }
        this.minQuantity = minQuantity;
    }

    public Packaging getPackaging()
    {
        return packaging;
    }

    public void setPackaging(Packaging packaging)
    {
        this.packaging = packaging;
    }

    public Vat getVat()
    {
        return vat;
    }

    public void setVat(Vat vat) throws ItemException
    {
        if(vat == null)
        {
            throw new ItemException("VAT cannot be null");
        }
        this.vat = vat;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Item item = (Item) obj;

        return Objects.equals(id, item.id) &&
                Objects.equals(label, item.label) &&
                Objects.equals(price, item.price) &&
                Objects.equals(restockQuantity, item.restockQuantity) &&
                Objects.equals(currentQuantity, item.currentQuantity) &&
                Objects.equals(emptyReturnableBottleQuantity, item.emptyReturnableBottleQuantity) &&
                Objects.equals(emptyReturnableBottlePrice, item.emptyReturnableBottlePrice) &&
                Objects.equals(forecastDate, item.forecastDate) &&
                Objects.equals(forecastQuantity, item.forecastQuantity) &&
                Objects.equals(minQuantity, item.minQuantity) &&
                Objects.equals(packaging, item.packaging) &&
                Objects.equals(vat, item.vat);
    }

    public static int hashCode(Integer id, String label, float price, int restockQuantity, int currentQuantity, int emptyReturnableBottleQuantity, float emptyReturnableBottlePrice, Date forecastDate, int forecastQuantity, int minQuantity, Packaging packaging, Vat vat)
    {
        return Objects.hash(id, label, price, restockQuantity, currentQuantity, emptyReturnableBottleQuantity, emptyReturnableBottlePrice, forecastDate, forecastQuantity, minQuantity, packaging, vat);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, label, price, restockQuantity, currentQuantity, emptyReturnableBottleQuantity, emptyReturnableBottlePrice, forecastDate, forecastQuantity, minQuantity, packaging, vat);
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

    public record RestockItem(int quantity, Date date, Event event)
    {
        public int getQuantity()
        {
            return quantity;
        }

        public Date getDate()
        {
            return date;
        }

        public Event getEvent()
        {
            return event;
        }
    }

}
