package Controller;


import BusinessLogic.Search.SearchItemManager;
import BusinessLogic.Search.SearchPaymentManager;
import BusinessLogic.SearchDocumentWithEventManager;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Search.GetMinMaxItemQuantityAndPriceException;
import Exceptions.Search.SearchItemException;
import Exceptions.Search.SearchPaymentException;
import Exceptions.Vat.UnkownVatCodeException;
import Exceptions.Vat.WrongVatCodeException;
import Model.Item.Item;
import Model.Payment.Payment;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class SearchController {
    private static final SearchDocumentWithEventManager searchDocumentWithEventManager = new SearchDocumentWithEventManager();
    private static SearchItemManager searchItemManager = new SearchItemManager();
    private static SearchPaymentManager searchPaymentManager = new SearchPaymentManager();  // Instantiating the SearchPaymentManager


    // function that retrieves all years of events (search by year for documents involving events)
    public static List<Integer> getDatesEvents() throws DatabaseConnectionFailedException{
        return searchDocumentWithEventManager.getDatesEvents();
    }

    public static ArrayList<Item> searchItem(String tvaCode, int minItem, int maxItem, int minPrice, int maxPrice) throws DatabaseConnectionFailedException, UnkownVatCodeException, SearchItemException, WrongVatCodeException {
        return searchItemManager.searchItem(tvaCode, minItem, maxItem, minPrice, maxPrice);
    }

    public static int[] getMinMaxItemQuantityAndPrice() throws DatabaseConnectionFailedException, GetMinMaxItemQuantityAndPriceException
    {
        return searchItemManager.getMinMaxItemQuantityAndPrice();
    }

    // Search for payments based on criteria (validated, amount, year)
    public static ArrayList<Payment> searchPayments(String paymentStatus, double minAmount, Date year) throws DatabaseConnectionFailedException, SearchPaymentException {
        return searchPaymentManager.searchPayments(paymentStatus, minAmount, year);  // Call the Payments Manager
    }
}