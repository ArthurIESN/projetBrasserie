package UnitTests;

import BusinessLogic.Document.DocumentManager;
import Exceptions.Customer.CountryException;
import Exceptions.Item.ItemException;
import Exceptions.Locality.LocalityException;
import Exceptions.Vat.VatException;
import Model.Country.Country;
import Model.Locality.Locality;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Model.Item.Item;
import Model.Vat.Vat;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class DocumentManagerTest
{

    private final DocumentManager documentManager = new DocumentManager();
    private static Locality locality;

    private static ArrayList<Item> items;
    private final HashMap<Item, Integer> itemsMap = new HashMap<>();

    @BeforeAll
    static void setUp()
    {
        try
        {
            Country country = new Country(12, "Code", 10.0f);
            locality = new Locality(23, "Addresse", "PostalCode", "City",  country);

            // generate 40 items
            items = new ArrayList<>();

            for (int i = 0; i < 5; i++)
            {
                Vat vat = new Vat("VAT 20", 20.0f);

                Item item = new Item(i, "Item" + i, 10.f, 0, 0, 0, 0, null, 0, 0, null, vat);
                items.add(item);
            }
        }
        catch (CountryException | LocalityException | ItemException | VatException e)
        {
            System.out.println("Error creating country: " + e.getMessage());
        }

    }


    @Test
    void calculateTaxes()
    {
        for (int i = 0; i <5; i++)
        {
            itemsMap.put(items.get(i), 3);
        }

        /*
         * 0 - Total Vat
         * 1 -  Total VAT Inclusive
         * 2 -  Total Delivery Cost
         * 3 - Total Price
         */
        float[] values = documentManager.calculateTaxes(itemsMap, locality);

        // for 5 items (with the same price)
        assertEquals(30.f, values[0]);
        assertEquals(180.f, values[1]);
        assertEquals(10.0f, values[2]);
        assertEquals(190.f , values[3]);

        // Dif quantity

        itemsMap.clear();
        itemsMap.put(items.get(0), 2);
        itemsMap.put(items.get(1), 4);

        values = documentManager.calculateTaxes(itemsMap, locality);
        assertEquals(12.f, values[0]);
        assertEquals(72.f, values[1]);
        assertEquals(10f, values[2]);
        assertEquals(82.f, values[3]);


        // Empty map
        itemsMap.clear();
        values = documentManager.calculateTaxes(itemsMap, locality);
        assertEquals(0f, values[0]);
        assertEquals(0f, values[1]);
        assertEquals(10f, values[2]);
        assertEquals(10f, values[3]);

        // locality null
        itemsMap.clear();
        itemsMap.put(items.get(0), 1);

        values = documentManager.calculateTaxes(itemsMap, null);
        assertEquals(2.f, values[0]);
        assertEquals(12.f, values[1]);
        assertEquals(0f, values[2]);
        assertEquals(12f, values[3]);


    }
}