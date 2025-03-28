package BusinessLogic;

import DataAccess.Search.SearchDocumentWithEventDBAccess;
import Exceptions.DataAccess.DatabaseConnectionFailedException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SearchDocumentWithEventManager {
    private final SearchDocumentWithEventDBAccess searchDocumentWithEventDBAccess = new SearchDocumentWithEventDBAccess();

    public List<Integer> getDatesEvents() throws DatabaseConnectionFailedException {
        List<Date> dates = searchDocumentWithEventDBAccess.getDatesEvents();
        List<Integer> retournableDates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (Date date : dates){
            calendar.setTime(date);
            retournableDates.add(calendar.get(Calendar.YEAR));
        }

        return retournableDates;
    }
}
