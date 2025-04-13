package Controller;


import BusinessLogic.Event.EventManager;
import BusinessLogic.Search.SearchItemManager;
import BusinessLogic.Search.SearchDocumentWithEventManager;

import BusinessLogic.Search.SearchPaymentManager;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Event.GetEventsWithItemException;
import Exceptions.Search.*;
import Exceptions.Vat.UnkownVatCodeException;
import Exceptions.Vat.WrongVatCodeException;
import Model.Document.Document;
import Model.Event.Event;
import Model.Item.Item;
import Model.Payment.Payment;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class SearchController {
    private static final SearchDocumentWithEventManager searchDocumentWithEventManager = new SearchDocumentWithEventManager();
    private static final SearchItemManager searchItemManager = new SearchItemManager();
    private static final SearchPaymentManager searchPaymentManager = new SearchPaymentManager();
    private static final EventManager searchEventManager = new EventManager();

    // fonction qui récupères toutes les années des event (recherche par années des documents impliquant des events)
    public static List<Integer> getDatesEvents(Integer idEvent) throws DatabaseConnectionFailedException{
        return searchDocumentWithEventManager.getDatesEvents(idEvent);
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

    public static ArrayList<Event> getEventsWithSpecificItem(int idItem) throws DatabaseConnectionFailedException, GetEventsWithItemException {
        return  searchEventManager.getEventsWithSpecificItem(idItem);
    }

    public static ArrayList<Float> getQuantityItemWithSpecificEvent(int idEvent) throws DatabaseConnectionFailedException, GetQuantityItemWithSpecificEventException {
        return searchDocumentWithEventManager.getQuantityItemWithSpecificEvent(idEvent);
    }

    public static ArrayList<Document> getDocumentsWithSpecificEvent(int idItem, int idEvent, float quantity, int year) throws DatabaseConnectionFailedException, GetDocumentWithSpecificEventException {
        return searchDocumentWithEventManager.getDocumentsWithSpecificEvent(idItem, idEvent, quantity, year);
    }

}