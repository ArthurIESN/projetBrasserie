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

    public ArrayList<Integer> getQuantityItemWithSpecificEvent(int idEvent, int idItem) throws GetQuantityItemWithSpecificEventException {
        return searchDocumentWithEventDBAccess.getQuantityItemWithSpecificEvent(idEvent, idItem);
    }

    public ArrayList<Document> getDocumentsWithSpecificEvent(int idItem, int idEvent, int quantity, int year) throws GetDocumentWithSpecificEventException{
        return searchDocumentWithEventDBAccess.getDocumentsWithSpecificEvent(idItem, idEvent, quantity, year);
    }

}
