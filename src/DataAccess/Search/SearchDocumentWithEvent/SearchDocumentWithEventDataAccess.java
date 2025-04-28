package DataAccess.Search.SearchDocumentWithEvent;

import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Search.GetQuantityItemWithSpecificEventException;

import java.util.Date;
import java.util.List;

public interface SearchDocumentWithEventDataAccess {
    List<Integer> getDatesEvents(Integer idEvent) throws DatabaseConnectionFailedException;
    List<Integer> getQuantityItemWithSpecificEvent(int idEvent) throws DatabaseConnectionFailedException, GetQuantityItemWithSpecificEventException;
}
