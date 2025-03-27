package Controller;


import BusinessLogic.Search.SearchItemManager;
import BusinessLogic.SearchDocumentWithEventManager;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Search.GetMinMaxItemQuantityAndPriceException;
import Exceptions.Search.SearchItemException;
import Exceptions.Vat.UnkownVatCodeException;
import Exceptions.Vat.WrongVatCodeException;
import Model.Item.Item;


import java.util.ArrayList;
import java.util.List;

public class SearchController {
    private static final SearchDocumentWithEventManager searchDocumentWithEventManager = new SearchDocumentWithEventManager();
    private static SearchItemManager searchItemManager = new SearchItemManager();

    // fonction qui récupères toutes les années des event (recherche par années des documents impliquant des events)
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
}