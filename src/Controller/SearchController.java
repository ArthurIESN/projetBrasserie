package Controller;


import BusinessLogic.Search.SearchItemManager;
import BusinessLogic.SearchDocumentWithEventManager;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.DataAccess.Search.GetMinMaxItemQuantityAndPriceException;
import Exceptions.tva.WrongTvaCodeException;
import Model.Item.Item;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchController {
    private static final SearchController instance = new SearchController();
    private final SearchDocumentWithEventManager searchDocumentWithEventManager = new SearchDocumentWithEventManager();
    private final SearchItemManager searchItemManager = new SearchItemManager();

    public SearchController(){}

    // fonction qui récupères toutes les années des event (recherche par années des documents impliquant des events)
    public List<Integer> getDatesEvents(){
        return searchDocumentWithEventManager.getDatesEvents();
    }

    public static SearchController getInstance(){
        return instance;
    }

    public ArrayList<Item> searchItem(String tvaCode, int minItem, int maxItem, int minPrice, int maxPrice) throws DatabaseConnectionFailedException, WrongTvaCodeException
    {
        return searchItemManager.searchItem(tvaCode, minItem, maxItem, minPrice, maxPrice);
    }

    public int[] getMinMaxItemQuantityAndPrice() throws DatabaseConnectionFailedException, GetMinMaxItemQuantityAndPriceException
    {
        return searchItemManager.getMinMaxItemQuantityAndPrice();
    }
}
