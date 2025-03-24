package BusinessLogic;

import DataAccess.SearchDocumentWithEventDBAccess;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SearchDocumentWithEventManager {
    private final SearchDocumentWithEventDBAccess searchDocumentWithEventDBAccess = new SearchDocumentWithEventDBAccess();
    public List<Integer> getDatesEvents(){
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
