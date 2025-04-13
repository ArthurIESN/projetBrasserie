package BusinessLogic.Search;

import DataAccess.Search.SearchDocumentWithEvent.SearchDocumentWithEventDBAccess;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Search.GetDocumentWithSpecificEventException;
import Exceptions.Search.GetQuantityItemWithSpecificEventException;
import Model.Document.Document;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SearchDocumentWithEventManager {
    private final SearchDocumentWithEventDBAccess searchDocumentWithEventDBAccess = new SearchDocumentWithEventDBAccess();

    public List<Integer> getDatesEvents(Integer idEvent) throws DatabaseConnectionFailedException {
        List<Integer> dates = searchDocumentWithEventDBAccess.getDatesEvents(idEvent);
        return dates;
    }

    public ArrayList<Float> getQuantityItemWithSpecificEvent(int idEvent) throws DatabaseConnectionFailedException, GetQuantityItemWithSpecificEventException {
        return searchDocumentWithEventDBAccess.getQuantityItemWithSpecificEvent(idEvent);
    }

    public ArrayList<Document> getDocumentsWithSpecificEvent(int idItem, int idEvent, float quantity, int year) throws DatabaseConnectionFailedException, GetDocumentWithSpecificEventException{
        return searchDocumentWithEventDBAccess.getDocumentsWithSpecificEvent(idItem, idEvent, quantity, year);
    }

}
