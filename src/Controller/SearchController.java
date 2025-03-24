package Controller;


import BusinessLogic.SearchDocumentWithEventManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchController {
    private static final SearchController instance = new SearchController();
    private final SearchDocumentWithEventManager searchDocumentWithEventManager = new SearchDocumentWithEventManager();

    public SearchController(){}

    // fonction qui récupères toutes les années des event (recherche par années des documents impliquant des events)
    public List<Integer> getDatesEvents(){
        return searchDocumentWithEventManager.getDatesEvents();
    }

    public static SearchController getInstance(){
        return instance;
    }
}
